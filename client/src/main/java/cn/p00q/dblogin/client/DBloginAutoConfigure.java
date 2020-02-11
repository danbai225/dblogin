/**
 * @author DanBai
 * @create 2020-02-09 22:28
 * @desc DBloginAutoConfigure
 **/
package cn.p00q.dblogin.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DBlogin.class)
@EnableConfigurationProperties(DBloginProperties.class)
public class DBloginAutoConfigure {
    @Autowired
    private DBloginProperties dblogin;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "danbai.dblogin", value = "serviceURL")
    DBlogin exampleService() {
        return new DBlogin(dblogin.getPasswordManagement(), dblogin.getServiceURL());
    }
}
