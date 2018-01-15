package cn.sp.chapter10;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Author: 2YSP
 * @Description: 注解处理器
 * @Date: Created in 2018/1/10
 */
//支持所有类型的Annotation
@SupportedAnnotationTypes("*")
//只支持jdk1.6的代码
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor{

    private NameChecker nameChecker;

    /**
     * 初始化名称检查插件
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个名称节点进行名称检查
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            for(Element element : roundEnv.getRootElements()){
                nameChecker.checkNames(element);
            }
        }
        return false;
    }
}
