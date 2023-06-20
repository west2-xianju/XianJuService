package com.xianju.CodeGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerartor {
    public static void main(String[] args) {

        String url = "jdbc:mysql:///xianjudatabase?useSSL=false&useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        String author = "feige";
        String outputDir = "D:\\west2五轮\\xianju\\src\\main\\java";
        String basePackage = "com.xianju";
        String moduleName = "demo";
        String mapperLocation = "D:\\west2五轮\\xianju\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "bid,goods,order,user,usercomment,wallet";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
