package good.job.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	@Bean
	public SecurityManager newSecurityManager(Realm realm) {
		DefaultWebSecurityManager sManager=
				new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager;
	}

	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean=
				new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/doLoginUI");
		//定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String,String> map=
				new LinkedHashMap<>();
		//静态资源允许匿名访问:"anon"
		map.put("/bootstrap/**","anon");
		map.put("/css/**","anon");
		map.put("/dist/**","anon");
		map.put("/font-awesome/**","anon");
		map.put("/fonts/**","anon");
		map.put("/images/**","anon");
		map.put("/Ionicons/**","anon");
		map.put("/js/**","anon");
		map.put("/layer/**","anon");
		map.put("/treegrid/**","anon");
		map.put("/ztree/**","anon");
		map.put("/iCheck/**","anon");
		//除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/user/doLogin","anon");
		map.put("/**","authc");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor
	newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor 
	newAuthorizationAttributeSourceAdvisor(
		    		    @Autowired SecurityManager securityManager) {
			        AuthorizationAttributeSourceAdvisor advisor=
					new AuthorizationAttributeSourceAdvisor();
			 return advisor;
	}
}
