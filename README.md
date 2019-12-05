# SpringBootByDatabaseAddCache
SpringBoot——数据库和缓存
### 1.Cuava cache
本地缓存，配置和应用都比较简单
~~~~java
@Configuration
@EnableCaching
public class GuavaCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(
                CacheBuilder.newBuilder().
                        expireAfterWrite(10, TimeUnit.SECONDS).
                        maximumSize(1000));
        return cacheManager;
    }
}
~~~~
这个配置其中的10，就是说10秒钟的缓存，1000就是说，最大数量是1000。

配置完这个以后，就是session层的事情了
~~~~
 @Cacheable(value = "user", key = "#userName")
    public List<User> getUsersByName( String userName ) {
        List<User> users = userMapper.getUsersByName( userName );
        System.out.println( "从数据库读取，而非读取缓存！" );
        return users;
    }
~~~~
@Cacheable就是这个方法里的值会被本地缓存
当然缓存当然不止这一个注解还有很多
比如：
1.@Cacheable：配置在 getUsersByName方法上表示其返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问

2.@CachePut：配置于方法上时，能够根据参数定义条件来进行缓存，其与 @Cacheable不同的是使用 @CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中，所以主要用于数据新增和修改操作上

3.@CacheEvict：配置于方法上时，表示从缓存中移除相应数据。

那如何使用这个方法呢？我们看Cotroller
~~~~ java
   @Autowired
    CacheManager cacheManager;

    @RequestMapping( value = "/getusersbyname", method = RequestMethod.POST)
    public String geUsersByName(@RequestBody User user ) {
        System.out.println( "-------------------------------------------" );
        System.out.println("call /getusersbyname");
        System.out.println(cacheManager.toString());
        List<User> users = userService.getUsersByName( user.getUsername() );
        return users.get(0).getUsername();
    }
~~~~
当然这个调用更定是不全面的，后面要跟进