package org.ilong.yuekeyun;

import com.github.tobato.fastdfs.service.TrackerClient;
import org.apache.commons.io.IOUtils;
import org.ilong.yuekeyun.Enum.ImgDir;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.utils.FastDFSClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@SpringBootTest
class YuekeyunApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    @Test
    void contextLoads() {
/*
        fastDFSClientUtil.deleteFile("http://139.196.39.179/group1/M00/00/00/rBEGzl-FMxWADWYtAAFCLcAQFoE461.png");
*/
/*
        fastDFSClientUtil.deleteFile("http://139.196.39.179/group1/M00/00/00/rBEGzl-FNNKAYdBIAAFCLcAQFoE928.png");
*/

        try {
            File file= new File("D:\\6.jpg");
            FileInputStream input = new FileInputStream(file);
             MockMultipartFile file1 = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

             String s = fastDFSClientUtil.uploadFile(file1,  ImgDir.LunBoTu.ItoByte());
             System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
  /*  @Test
    void contextLoads() {
         AuthUser authUser = new AuthUser();
         authUser.setUsername("zengxianlong");
         authUser.setRealname("曾老师");
         authUser.setMobile("13750466450");
         authUser.setPassword("admin");
        System.out.println(         passwordEncoder.encode(authUser.getPassword())
);*/

         /* @Test
        public void testAddPerson() {
            Person person =new Person();
            person.setUsername("yue");
            person.setPassword("123456");
            personService.addPerson(person);
        }*/

        /*@Test
        public void uploadFile() throws Exception {
             //1、添加fastdfs_client.jar
             //2、创建配置文件，说明tracker_server的地址
             //3、加载配置文件
             ClientGlobal.init("C:/个人文件/taotao/taotao-manager-web/src/main/resources/fastdfs/conf.properties");
             //4、创建trackerClient对象
             TrackerClient trackerClient = new TrackerClient();
             //5、获取trackerServer
             TrackerServer trackerServer = trackerClient.getConnection();
             //6、创建storageServer
             StorageServer storageServer = null;
             //7、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
             StorageClient storageClient = new StorageClient(trackerServer, storageServer);
             //8、使用StorageClient对象上传图片。
             String[] adds = storageClient.upload_file("C:\\Users\\Liu-Zhoujian\\Pictures\\hacker.jpg", "jpg", null);
             //9、返回地址
             for (String string : adds) {
                 System.out.println(string);
             }
         }
*/


    }


