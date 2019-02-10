/**
 * @description
 */
package late.todo.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import late.todo.prop.AliyunProperties;
import late.todo.service.IIPRemoteService;
import lombok.extern.slf4j.Slf4j;

/**
 * 定时修改阿里云DNS配置
 * 
 * @projectName todo
 * @packageName late.todo.scheduled
 * @fileName AliyunDnsScheduled.java
 * @author chijingjia
 * @createTime :2019年2月10日 下午4:45:50
 * @version: v1.0
 */
@Component
@Slf4j
public class AliyunDnsScheduled {
	@Autowired
	IIPRemoteService ipRemoteService;
	@Autowired
	AliyunProperties aliyunProperties;

	@Value("${late.mail.sendto}")
	private String mailSendTo;

	@Scheduled(cron = "0 1/5 * * * ?")
	public void updateDnsInternetIp() {
		if (client == null) {
			IClientProfile profile = DefaultProfile.getProfile(regionId, aliyunProperties.getAccessKeyId(),
					aliyunProperties.getAccessKeySecret());
			client = new DefaultAcsClient(profile);
		}

		aliyunDomain();

		return;
	}

	/**
	 * 更新dns处理
	 * 
	 * @methodName aliyunDomain
	 * @author chijingjia
	 * @createTime 2019年2月10日 下午4:55:48
	 * @version v1.0
	 */
	private void aliyunDomain() {
		DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
		request.setDomainName(aliyunProperties.getDomainName());
		DescribeDomainRecordsResponse response;
		try {
			response = client.getAcsResponse(request);
			Record record = response.getDomainRecords().get(0);
			String old_ip = record.getValue();
			String cur_ip = ipRemoteService.getInternetIP();

			if (!old_ip.equals(cur_ip)) {
				log.info("修改为" + cur_ip);
				UpdateDomainRecordRequest udr_req = new UpdateDomainRecordRequest();
				udr_req.setValue(cur_ip);
				udr_req.setType(record.getType());
				udr_req.setTTL(record.getTTL());
				udr_req.setPriority(record.getPriority());
				udr_req.setLine(record.getLine());
				udr_req.setRecordId(record.getRecordId());
				udr_req.setRR(record.getRR());
				@SuppressWarnings("unused")
				UpdateDomainRecordResponse udr_resp = client.getAcsResponse(udr_req);
			} else {
				log.info(cur_ip + " 不需要修改");
			}
		} catch (ServerException e1) {
			e1.printStackTrace();
		} catch (ClientException e1) {
			e1.printStackTrace();
		}
	}

	private static IAcsClient client = null;
	private static final String regionId = "cn-hangzhou";
}
