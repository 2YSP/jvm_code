package cn.sp.chapter10;


import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/1/10
 */
public class NameChecker {

    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnv){
        this.messager = processingEnv.getMessager();
    }

    /**
     * 类或接口：符合驼式命名法，首字母大写
     * 方法：符合驼式命名法，首字母小写
     * 实例变量：符合驼式命名法，首字母小写
     * 常量：全部要求大写
     * @param element
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    private class NameCheckScanner extends ElementScanner6<Void,Void>{
        /**
         * 此方法用于检查Java类
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void p) {
            scan(e.getTypeParameters(),p);
            checkCamelCase(e,true);
            return super.visitType(e, p);
        }

        /**
         * 检查方法命名
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void p) {
            if (e.getKind() == ElementKind.METHOD){
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())){
                    messager.printMessage(Diagnostic.Kind.WARNING,"一个普通方法"+name+"不应与类名重复，" +
                            "避免与构造函数产生混淆",e);
                    checkCamelCase(e,false);
                }
            }
            return super.visitExecutable(e, p);
        }

        /**
         * 检查变量命名
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void p) {
            //如果这个Variable是枚举或常量，则按大写命名检查，否则按驼式命名法规则检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null ||
                    heuristicallyConstant(e)){
                checkAllCaps(e);
            }else {
                checkCamelCase(e,false);
            }
            return null;
        }

        /**
         * 判断一个变量是否为常量
         * @param e
         * @return
         */
        private boolean heuristicallyConstant(VariableElement e){
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE){
                return true;
            }else if (e.getKind() == ElementKind.FIELD &&
                    e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL))){
                return true;
            }else {
                return false;
            }
        }

        //检查传入的element 是否符合驼式命名法，如果不符合则输出警告信息
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstPointCode = name.codePointAt(0);

            if (Character.isUpperCase(firstPointCode)){
                previousUpper = true;
                if (!initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以小写字母开头",e);
                    return;
                }
            }else if (Character.isLowerCase(firstPointCode)){
                if (initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以小写字母开头",e);
                    return;
                }
            }else {
                conventional = false;
            }

            if (conventional){
                int cp = firstPointCode;
                for(int i = Character.charCount(cp);i < name.length();i += Character.charCount(cp)){
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)){
                        if (previousUpper){
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    }else {
                        previousUpper = false;
                    }
                }
            }

            if (!conventional){
                messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当符合驼式命名法(Camel Case Names)",e);

            }
        }

        /**
         * 大写命名检查，要求第一个必须是大写的英文字母，其余部分可以使下划线或者大写字母
         * @param e
         */
        private void checkAllCaps(Element e){
            String name = e.getSimpleName().toString();
            boolean conventional = true;
            int firstPointCode = name.codePointAt(0);

            if (!Character.isUpperCase(firstPointCode)){
                conventional = false;
            }else {
                boolean previousUnderscore = false;
                int cp = firstPointCode;
                for (int i=Character.charCount(cp); i < name.length(); i += Character.charCount(cp)){
                    cp = name.codePointAt(i);
                    if (cp == (int)'_'){
                        if (previousUnderscore){
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    }else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional){
                messager.printMessage(Diagnostic.Kind.WARNING,"常量"+name+"应当全部以大写字母或下划线命名，并且以字母开头",e);
            }
        }
    }
}
