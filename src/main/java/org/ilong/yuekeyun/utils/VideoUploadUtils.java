package org.ilong.yuekeyun.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;

/**
 * 视频处理工具。
 *
 * @author long
 * @date 2020-12-07 15:13
 */
@Component
public class VideoUploadUtils {


    /**
     *   // 音频信息
     *         AudioInfo audio = info.getAudio();
     *         int bitRate = audio.getBitRate();  // 比特率
     *         int channels = audio.getChannels();  // 声道
     *         String decoder = audio.getDecoder();  // 解码器
     *         int sRate = audio.getSamplingRate();  // 采样率
     *         System.out.println("解码器：" + decoder + "，声道：" + channels + "，比特率：" + bitRate + "，采样率：" + sRate);
     *         // 视频信息
     *         VideoInfo video = info.getVideo();
     *         int bitRate2 = video.getBitRate();
     *         Float fRate = video.getFrameRate();  // 帧率
     *         VideoSize videoSize = video.getSize();
     *         int height = videoSize.getHeight();  // 视频高度
     *         int width = videoSize.getWidth();  // 视频宽度
     *         System.out.println("视频帧率：" + fRate + "，比特率：" + bitRate2 + "，视频高度：" + height + "，视频宽度：" + width);
     * @param url
     * @return
     */
    public static String ReadVideoTime(String url){
        File mediaFile = new File(url);
        FFmpegFileInfo fFmpegFileInfo=new FFmpegFileInfo(mediaFile);
        MultimediaInfo multimediaInfo=null;
        String length="";
        try {
            multimediaInfo=fFmpegFileInfo.getInfo(url);
            long ls = multimediaInfo.getDuration()/ 1000;
            Integer hour = (int) (ls / 3600);
            Integer minute = (int) (ls % 3600) / 60;
            Integer second = (int) (ls - hour * 3600 - minute * 60);
            String hr = hour.toString();
            String mi = minute.toString();
            String se = second.toString();
            if (hr.length() < 2) {
                hr = "0" + hr;
            }
            if (mi.length() < 2) {
                mi = "0" + mi;
            }
            if (se.length() < 2) {
                se = "0" + se;
            }
            length = hr + ":" + mi + ":" + se;

        }catch (Exception e){
            e.printStackTrace();
        }
        return length;

    }

    /**
     * 获取视频时长
     * @param file
     * @return
     */
   /* public static String ReadVideoTime(MultipartFile file){
        //获取文件原始名
        String filename = file.getOriginalFilename();
        File tempFile = new File(System.getProperty("java.io.tmpdir"), filename);
        String length = "";
        try {
            // 先把文件序列化到临时目录
            file.transferTo(tempFile);
            FFmpegFileInfo ffmpegFileInfo   = new FFmpegFileInfo(tempFile);
            MultimediaInfo multimediaInfo = null;
            ffmpegFileInfo.getInfo(url)


            long ls = result.getDuration() / 1000;
            Integer hour = (int) (ls / 3600);
            Integer minute = (int) (ls % 3600) / 60;
            Integer second = (int) (ls - hour * 3600 - minute * 60);
            String hr = hour.toString();
            String mi = minute.toString();
            String se = second.toString();
            if (hr.length() < 2) {
                hr = "0" + hr;
            }
            if (mi.length() < 2) {
                mi = "0" + mi;
            }
            if (se.length() < 2) {
                se = "0" + se;
            }
            length = hr + ":" + mi + ":" + se;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 响应客户端后，始终删除临时文件
            tempFile.delete();
        }
        return length;

    }
*/
    /**
     * 视频大小
     * @param file
     * @return
     */
    @SuppressWarnings({"resource"})
    public static String ReadVideoSize(MultipartFile file){
        //获取文件原始名
        String filename = file.getOriginalFilename();
        File tempFile = new File(System.getProperty("java.io.tmpdir"), filename);
        FileChannel fc = null;
        String size = "";
        try {
            FileInputStream fis = new FileInputStream(tempFile);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize.divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_UP) + "MB";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tempFile.delete();
        }
        return size;
    }

}
