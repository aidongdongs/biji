### 使用springboot上传头像

- 前端代码： form表单需要改为post请求，应为get请求只支持2kb的上传 

- 前端代码：form表单需要添加 enctype=“mulipart/form-data”属性，表示这个表单上传的是文件 ，如果不使用这个属性的话默认的www， 什么的属性会对表单上传的属性进行字符串的切割，使用这个属性就是让浏览器不做切割操作

  ```html
  	<form action="/user/change_avatar" id="form-change-avatar" enctype="multipart/form-data" method="post"  class="form-horizontal" role="form">
  </form>
  ```

- 后端接口：在接口的形参列表中添加mulipartFile属性，使用该属性可以让spring在form表单提交中有文件的时候，spring会帮我们将数据自动注入到这个属性上

  ```java
      @PostMapping("/change_avatar")
      public JsonResult changeAvatar(HttpSession session,
                                    @RequestParam("file") MultipartFile file)
      {
  ```

- 判断上传的文件是否为空，为空抛出异常

  ```java
  //判断文件是否为空
          if (file.isEmpty()){
              throw new FileEmptyException("文件为空");
          }
  ```

- 判断文件大小是否正常

  ```java
       
     /**设置文件上传的最大值*/
     	 public static final int AVATAR_MAX_SIZE=10*1024*1024;
  	//文件大小是否正常
          if (file.getSize()>AVATAR_MAX_SIZE){
              throw new FileSizeException("文件大小异常");
          }
  ```

- 设置文件上传的类型

  ```java
      /** 设置文件上传的类型*/
      public static final List<String> AVATAR_TYPE= new ArrayList<>();
      //初始化上传类型
      static {
          AVATAR_TYPE.add("image/jpeg");
          AVATAR_TYPE.add("image/png");
          AVATAR_TYPE.add("image/bmp");
          AVATAR_TYPE.add("image/gif");
      	}
          //判断文件类型是否是我们规定的后缀类型
          String contentType = file.getContentType(); //getContextType 返回的结果是image/后缀名
          System.out.println(contentType);
  
          //contains 如果集合包含了某个元素则返回true
          if (!AVATAR_TYPE.contains(contentType)){
              throw  new FileTypeException("文件类型不支持");
          }
  ```

- 获取服务器的决定物理地址，然后创建一个文件目录，用来存放上传的文件

  ```java
    //获取项目服务的绝对物理地址+upload
  		//session.getRealPath（）获取的是服务器的绝地地址 形参1=》 绝对地址+形参
          String realPath = session.getServletContext().getRealPath("upload");
          System.out.println(realPath);
     //使用 绝对物理地址+upload 创建一个File对象
          File dir = new File(realPath);
  
        //不知道这个文件是否存在  ，不存在我们就创建目录
         if (!dir.exists()){
              System.out.println("文件不存在");
              dir.mkdirs(); //创建目录
          }
  ```

- 将文件存在到创建好的目录中，但是文件的名字是需要唯一的，不然可能出现文件覆盖

  ```java
         //获取上传过来的头像的名字
          String originalFilename = file.getOriginalFilename(); //getOriginalFilename（）获取上传文件的名称
   		//获取上传文件的 后追命
          String suffix = originalFilename
                  .substring(originalFilename.lastIndexOf("."),originalFilename.length());
          //给上传的文件生成一个新的随机名-》不重复  名字+上床文件的后缀名
          String fileName = UUID.randomUUID().toString().toUpperCase()+suffix;
  ```

- 存储文件

  ```java
     //使用新文件全名创建一个file对象 在创建好的目录下创建  名字+上床文件的后缀名
          File dest = new File(dir,fileName); //file方法的重载:形参1：存储文件的目录 形参2  存储的文件
  		// dest 是一个已经使用 不重名UUID+源文件后缀 生成的一个的文件对象
    		try {
              file.transferTo(dest); //将file 文件中的数据写入到dest文件中 但是上传文件和这个文件的类型要一样
          } catch (FileStateException e){
              throw  new FileStateException("文件状态异常");
          } catch (IOException e) {
              e.printStackTrace();
              throw new FileUploadIOException("文件读写异常");
  
          }
  ```

- 调用完成业务方法

  ```java
          Integer uid = getuidFromSession(session);
          String username = getUsernameFromSession(session);
          //返回头像的路径
          String avatar = "/upload"+fileName;
          userService.changeAvatar(uid,avatar,username);
          return new JsonResult(UPDATE_OK,"上传头像成功",null);
  ```

  



### 解决bug   	设置springboot 中springmvc默认的大小  	默认1mb

**方式1：配置文件**

```yaml
  servlet:
    multipart:
      max-file-size 10mb: #单个文件的大小
      max-request-size 20mb: #整个请求的大小
```

**方式2：java代码控制  主类进行配置 **

```java
    //配置上传文件的大小
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        //配置工厂类对象
        MultipartConfigFactory factory =  new MultipartConfigFactory();
        //设置属性
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES)); //文件最大的大小
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));
        //通过工厂类创建MultipartConfigElement对象
        return factory.createMultipartConfig();
    }
```



### 省时 完整代码

```java
 @PostMapping("/change_avatar")
    public JsonResult changeAvatar(HttpSession session,
                                  @RequestParam("file") MultipartFile file)
    {
        //判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //文件大小是否正常
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小异常");
        }
        //判断文件类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        System.out.println(contentType);
        //contains 如果集合包含了某个元素则返回true
        if (!AVATAR_TYPE.contains(contentType)){
            throw  new FileTypeException("文件类型不支持");
        }
        //获取项目服务的绝对物理地址+upload
        String realPath = session.getServletContext().getRealPath("upload");
        //使用 绝对物理地址+upload 创建一个File对象
        File dir = new File(realPath);
        //不知道这个文件是否存在  ，不存在我们就创建目录
        if (!dir.exists()){
            System.out.println("文件存在");
            dir.mkdirs(); //创建目录
        }
       //获取上传过来的头像的名字
        String originalFilename = file.getOriginalFilename();
        //获取上传文件的 后追命
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf("."),originalFilename.length());
        //给上传的文件生成一个新的随机名-》不重复  名字+上床文件的后缀名
        String fileName = UUID.randomUUID().toString().toUpperCase()+suffix;
        //使用新文件全名创建一个file对象 在创建好的目录下创建  名字+上床文件的后缀名
        File dest = new File(dir,fileName);

        try {
            file.transferTo(dest); //将file 文件中的数据写入到dest文件中
        } catch (FileStateException e){
            throw  new FileStateException("文件状态异常");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadIOException("文件读写异常");

        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径
        String avatar = "/upload"+fileName;
        userService.changeAvatar(uid,avatar,username);
        return new JsonResult(UPDATE_OK,"上传头像成功",null);
    }
```

