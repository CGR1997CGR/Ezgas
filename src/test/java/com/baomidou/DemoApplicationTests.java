package com.baomidou;

import com.baomidou.commonutils.MD5;
import com.baomidou.entity.Report;
import com.baomidou.entity.User;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.service.ReportService;
import com.baomidou.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;
    @Test
    void contextLoads() {
        List<User> users = userService.list(null);
        users.forEach((System.out::println));

    }
    @Test
    void registor(){
        for(Integer i=0;i <=30;i ++){
            User user = new User();
            String s=Integer.toString(i);
            String str = "zyx" + s;
            user.setUsername(str);
            user.setEmail(str);
            user.setPassword(MD5.encrypt(str));
            userService.save(user);
        }
    }

    @Test
    void delete(){
        boolean res = userService.removeById("1512717776225878018");
        System.out.println(res);
    }
    @Test
    void insertreport(){
        QueryWrapper<Report> queryWrapper = new QueryWrapper();
        queryWrapper.eq("gas_station_name","TAIHE123");
        if(reportService.count(queryWrapper)>0){
            Report report = reportService.getOne(queryWrapper);
            report.setPriceOfDiesel(520.0);
            report.setPriceOfGas(123.0);
            report.setPriceOfMethan(123.0);
            report.setPriceOfLpg(123.0);
            report.setWaitingTime("80");
            reportService.updateById(report);
        }else{
            Report report = new Report();
            report.setGasStationName("TAIHE123");
            report.setUserid("123");
            report.setPriceOfDiesel(0.00);
            report.setPriceOfGas(0.00);
            report.setPriceOfMethan(0.00);
            report.setPriceOfLpg(0.00);
            report.setWaitingTime("00");
            reportService.save(report);
        }
    }


    @Test
    public void main1() {
        // 1????????????????????????
        AutoGenerator mpg = new AutoGenerator();
        // 2???????????????
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(projectPath + "/src/main/java");
        //gc.setOutputDir(projectPath);
        gc.setAuthor("zhangyuexin");
        gc.setOpen(false); //????????????????????????????????????
        gc.setFileOverride(false); //?????????????????????????????????
        /*
         * mp??????service???????????????????????????????????????????????? I
         * UcenterService
         * */
        gc.setServiceName("%sService"); //??????Service??????????????????I
        gc.setIdType(IdType.ID_WORKER); //????????????
        gc.setDateType(DateType.ONLY_DATE);//???????????????????????????????????????
        //gc.setSwagger2(false);//??????Swagger2??????
        mpg.setGlobalConfig(gc);
        // 3??????????????????
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/ezgas?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        // 4????????????
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("serviceedu"); //?????????
        //pc.setParent("");
        //pc.setParent("demo");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
        // 5???????????????
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("report");
        strategy.setNaming(NamingStrategy.underline_to_camel);//??????????????????????????????????????????
        strategy.setTablePrefix(pc.getModuleName() + "_"); //??????????????????????????????
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//????????????????????????????????????????????????
        strategy.setEntityLombokModel(true); // lombok ?????? @Accessors(chain =true) setter????????????
        strategy.setRestControllerStyle(true); //restful api???????????????
        strategy.setControllerMappingHyphenStyle(true); //url?????????????????????
        mpg.setStrategy(strategy);
        // 6?????????
        mpg.execute();
    }
    }
