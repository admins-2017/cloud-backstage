package com.giantlizardcloud.poiTest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.giantlizardcloud.config.poi.entity.StudentEntity;
import com.giantlizardcloud.config.poi.entity.TeacherEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SpringBootTest
class PoiTests {
//
//    @Test
//    public void testExcel() throws IOException {
//        List<StudentEntity> list = new ArrayList<>();
//        list.add(new StudentEntity("1", "康东伟", 1, new Date(), new Date()));
//        list.add(new StudentEntity("2", "赵佳旺", 1, new Date(), new Date()));
//        list.add(new StudentEntity("3", "袁依群", 2, new Date(), new Date()));
//        list.add(new StudentEntity("4", "于建明", 1, new Date(), new Date()));
//        list.add(new StudentEntity("5", "张君洋", 1, new Date(), new Date()));
//
//
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
//                StudentEntity.class, list);
//
//        File savefile = new File("E:\\temp\\easypoi");
//        if (!savefile.exists()) {
//            boolean result = savefile.mkdirs();
//            System.out.println("目录不存在，创建" + result);
//        }
//        FileOutputStream fos = new FileOutputStream("E:\\temp\\easypoi\\test1.xls");
//        workbook.write(fos);
//        fos.close();
//    }
//
//    @Test
//    public void testExcel2() throws IOException {
//        List<StudentEntity> studentList = new ArrayList<>();
//        studentList.add(new StudentEntity("1", "康东伟", 1, new Date(), new Date()));
//        studentList.add(new StudentEntity("2", "赵佳旺", 1, new Date(), new Date()));
//        studentList.add(new StudentEntity("3", "袁依群", 2, new Date(), new Date()));
//        studentList.add(new StudentEntity("4", "于建明", 1, new Date(), new Date()));
//        studentList.add(new StudentEntity("5", "张君洋", 1, new Date(), new Date()));
//        List<TeacherEntity> teacherList = new ArrayList<>();
//        teacherList.add(new TeacherEntity("1", "康东伟", studentList));
//        teacherList.add(new TeacherEntity("2", "赵佳旺", studentList));
////
////        List<CourseEntity> courseList = new ArrayList<>();
////        courseList.add(new CourseEntity("1", "java", teacherList));
////        courseList.add(new CourseEntity("2", "web", teacherList));
//
////        courseList.forEach(System.out::println);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("明细", "测试", "测试"),
//                TeacherEntity.class, teacherList);
//
//        File savefile = new File("E:\\temp\\easypoi");
//        if (!savefile.exists()) {
//            boolean result = savefile.mkdirs();
//            System.out.println("目录不存在，创建" + result);
//        }
//        FileOutputStream fos = new FileOutputStream("E:\\temp\\easypoi\\test7.xls");
//        workbook.write(fos);
//        fos.close();
//    }
//
//    @Test
//    public void fe_map() throws Exception {
//
//        File file = new ClassPathResource("templates/poi/doc/测试专项支出用款申请书.xls").getFile();
//
//        System.out.println(file.getPath());
//
//
//        TemplateExportParams params = new TemplateExportParams(
//                file.getPath());
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2014-12-25");
//        map.put("money", 2000000.00);
//        map.put("upperMoney", "贰佰万");
//        map.put("company", "执笔潜行科技有限公司");
//        map.put("bureau", "财政局");
//        map.put("person", "JueYue");
//        map.put("phone", "1879740****");
//        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//        for (int i = 0; i < 4; i++) {
//            Map<String, String> lm = new HashMap<String, String>();
//            lm.put("id", i + 1 + "");
//            lm.put("zijin", i * 10000 + "");
//            lm.put("bianma", "A001");
//            lm.put("mingcheng", "设计");
//            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
//            lm.put("quancheng", "开源项目");
//            lm.put("sqje", i * 10000 + "");
//            lm.put("hdje", i * 10000 + "");
//
//            listMap.add(lm);
//        }
//        map.put("maplist", listMap);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//        File savefile = new File("D:/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
//        workbook.write(fos);
//        fos.close();
//    }
//
//
//    @Test
//    public void fe_map2() throws Exception {
//
//        File file = new ClassPathResource("templates/poi/doc/专项支出用款申请书_map.xls").getFile();
//
//        System.out.println(file.getPath());
//
//
//        TemplateExportParams params = new TemplateExportParams(
//                file.getPath());
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2014-12-25");
//        map.put("money", 2000000.00);
//        map.put("upperMoney", "贰佰万");
//        map.put("company", "执笔潜行科技有限公司");
//        map.put("bureau", "财政局");
//        map.put("person", "JueYue");
//        map.put("phone", "1879740****");
//        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//        for (int i = 0; i < 4; i++) {
//            Map<String, String> lm = new HashMap<String, String>();
//            lm.put("id", i + 1 + "");
//            lm.put("zijin", i * 10000 + "");
//            lm.put("accountType", "对公账户");
//            lm.put("bianma", "A001");
//            lm.put("kaihu", "内蒙古呼和浩特市分行");
//            lm.put("yinhang", "中国银行");
//            lm.put("mingcheng", "设计");
//            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
//            lm.put("quancheng", "开源项目");
//            lm.put("sqje", i * 10000 + "");
//            lm.put("hdje", i * 10000 + "");
//
//            listMap.add(lm);
//        }
//        map.put("maplist", listMap);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//        File savefile = new File("D:/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书6_map.xls");
//        workbook.write(fos);
//        fos.close();
//    }
//
//
//    /**
//     * 导出中文数据
//     * @throws IOException
//     * @throws DocumentException
//     */
//    @Test
//    public void testPDF() throws IOException, DocumentException {
//        Document document = new Document();//1.创建document实例
//        FileOutputStream out=new FileOutputStream(new File("E:/testPdf/test4.pdf"));
//        PdfWriter writer = PdfWriter.getInstance(document, out);//2.创建PdfWriter 实例，并指定输出路径。
//        document.open();//3. 打开 document实例，开始想document中添加内容
//        BaseFont bfComic = BaseFont.createFont("c:\\windows\\fonts\\simkai.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        //添加内容
//        Font font = new Font(bfComic, 14,Font.ITALIC);
//        document.add(new Paragraph("测试.......",font));//4.添加内容
//        document.close();//5. 关闭
//    }

}
