package com.wzl.cloud;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MicroOauth2ApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        pdf2png("C:\\Users\\Administrator\\Documents\\Tencent Files\\431426221\\FileRecv", "2-《保险条款》", "jpg");
    }


    /**
     * 转换全部的pdf
     *
     * @param fileAddress 文件地址
     * @param filename    PDF文件名
     * @param type        图片类型
     */
    public static void pdf2png(String fileAddress, String filename, String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(fileAddress + "\\" + filename + ".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 72); // Windows native DPI
//                 BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File("G:\\a\\wzl\\" + filename + "_" + (i + 1) + "." + type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test1() throws InterruptedException, PDFSecurityException, PDFException, IOException {
        String filePath = "C:\\Users\\Administrator\\Documents\\Tencent Files\\431426221\\FileRecv\\2-《保险条款》.pdf";
        pdf2Pic(filePath, "d:\\wzl\\a");
    }

    /**
     * @author: GPJ
     * @Description: pdf 转单张高清图片
     * @Date Created in 9:27 2018/1/9
     * @Modified By:
     */
    public static void pdf2Pic(String pdfPath, String path) throws IOException, PDFException, PDFSecurityException, InterruptedException {
        Document document = new Document();
        document.setFile(pdfPath);
        //缩放比例
        float scale = 1.0f;
        //旋转角度
        float rotation = 0f;

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String imgName = i + ".jpg";
                System.out.println(imgName);
                File file = new File(path + imgName);
                ImageIO.write(rendImage, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    /**
     * 将pdf文件转成HDjpg图片
     *
     * @param filePath
     * @param path
     */

    public static void pdfToHDjpg(String filePath, String path) {
        try {
            Document document = new Document();

            document.setFile(new File(filePath).getPath());
            //缩放比例
            float scale = 2.5f;
            //旋转角度
            float rotation = 0f;
            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                                org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                image.flush();
                piclist.add(image);
            }
            yPic(piclist, path);
            document.dispose();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    @Test
    void test2() {
        String filePath = "C:\\Users\\Administrator\\Documents\\Tencent Files\\431426221\\FileRecv\\2-《保险条款》.pdf";
        pdfToHDjpg(filePath, "D:\\45121245.jpg");

    }

    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     *
     * @param piclist 文件流数组
     * @param outPath 输出路径
     */
    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer
                        .getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_BGR);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0)
                    _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i),
                        0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(imageResult, "jpg", out);// 写图片
            byte[] b = out.toByteArray();
            FileOutputStream output = new FileOutputStream(outFile);
            output.write(b);
            out.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        String filePath = "D:\\a.pdf";
//        pdfToHDjpg(filePath, "D:\\b.jpg");
//    }

    @Test
    void test11() {
        System.out.println(System.getProperties());
        System.out.println(System.getProperties());
        Hello hello = new Hello();
        for (int i = 0; i < 50_000; i++) {
            System.out.println("i ======================= " + i);
            hello.test();
        }
    }


    public static class Hello {
        public void test() {
            int i = 8;
            while ((i -= 3) > 0);
            System.out.println("i = " + i);
        }

    }

    private static Integer num = 0;
    private static Integer count = 0;
    @Test
    void test22() {
        List<Integer> test = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            test.add((int) (Math.random() * 10000));
        }
        List<Integer> arr = new ArrayList<>(test);

//        Arrays.stream(arr).forEach(System.out::println);
        int temp = 0;
        List<Integer> arrs = new ArrayList<>(test);
        Long b = System.currentTimeMillis();
        for (int i = 0; i < arrs.size(); i++) {
            for (int j = i+1; j < arrs.size(); j++) {
                if (arrs.get(i) > arrs.get(j)) {
                    temp = arrs.get(j);
                    arrs.set(j, arrs.get(i));
                    arrs.set(i, temp);
                }
                count++;
            }
        }
        System.out.println("num ========= " + count + "\n" + "time ====" + (System.currentTimeMillis() - b));
//        Arrays.stream(arrs).forEach(System.out::println);
        Long a = System.currentTimeMillis();
        fastOrder(arr, 0, arr.size() - 1);
//        quickSort(arr);
        System.out.println("num ========= " + num + "\n" + "time ====" + (System.currentTimeMillis() - a));
        System.out.println("hh");
    }

    void fastOrder(List<Integer> array, int begin, int end) {
        if (begin >= end||end< 0) {
            return;
        }
        int relatively = end;
        int temp = array.get(begin);
        while (begin < end) {

//            while (true) {
//                if (array[end] <= temp&& begin < end) {
//                    array[begin] = array[end];
//                    begin++;
//                    break;
//                }else{
//                    end--;
//                }
//            }
//            while (true) {
//                if (array[begin] > temp && begin < end) {
//                    array[end] = array[begin];
//                    end--;
//                    break;
//                } else {
//                    begin++;
//                }
//            }
            while (begin < end && array.get(end) > temp) {
//                num++;
                end--;
            }
            if (begin < end) {
                array.set(begin, array.get(end));
            }

            while (begin < end && array.get(begin) <= temp) {
//                num++;
                begin++;
            }
            if (begin < end) {
                array.set(end, array.get(begin));
            }
        }
        array.set(begin,temp);
        fastOrder(array, 0, begin - 1);
        fastOrder(array, begin + 1, relatively);
    }





    private static int partition(List<Integer> arr, int low, int high) {
        //指定左指针i和右指针j
        int i = low;
        int j= high;

        //将第一个数作为基准值。挖坑
        int x = arr.get(low);

        //使用循环实现分区操作
        while(i<j){//5  8
            //1.从右向左移动j，找到第一个小于基准值的值 arr[j]
            while(arr.get(j)>=x && i<j){
                count++;
                j--;
            }
            //2.将右侧找到小于基准数的值加入到左边的（坑）位置， 左指针想中间移动一个位置i++
            if(i<j){
                arr.set(i, arr.get(j));
                i++;
            }
            //3.从左向右移动i，找到第一个大于等于基准值的值 arr[i]
            while(arr.get(i)<x && i<j){
                i++;
                count++;
            }
            //4.将左侧找到的打印等于基准值的值加入到右边的坑中，右指针向中间移动一个位置 j--
            if(i<j){
                arr.set(j, arr.get(i));
                j--;
            }
        }

        //使用基准值填坑，这就是基准值的最终位置
        arr.set(i, x);//arr[j] = y;
        //返回基准值的位置索引
        return i; //return j;
    }
    private static void quickSort(List<Integer> arr, int low, int high) {//???递归何时结束
        if(low < high){
            //分区操作，将一个数组分成两个分区，返回分区界限索引
            int index = partition(arr,low,high);
            //对左分区进行快排
            quickSort(arr,low,index-1);
            //对右分区进行快排
            quickSort(arr,index+1,high);
        }

    }

    public static void quickSort(List<Integer> arr) {
        int low = 0;
        int high = arr.size()-1;
        quickSort(arr,low,high);
    }






}