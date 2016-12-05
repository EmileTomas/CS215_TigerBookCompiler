# 语义分析(chapter 5)

## 实验目的

通过符号表的构建,对`Parsing` 输出的抽象语法树进行类型检查.

本实验虎书并未提供现成框架,整个`Semant` package需要自己建立,当然虎书在pdf中已经提供了大部分的思路和实例代码了.主要工作便是将示例代码推广以及解决其中遇见的各种特殊情况.相比与词法分析与语法分析需要对课本有更深刻的掌握.

## 实验说明

### 实现说明

1. 符号表采用`Imperative Symbol Table` ,通过建立一个`undo`栈实现不同**环境**的保存.
2. 整个分析过程有两个**环境**(符号表实例),分别是`Tenv`和`Venv`. 

   - `Tenv`: Type Environment, 用于记录Type项的相关信息
   - `Venv`:Value Environment, 用于记录Function以及Variables
3. 类型检查有三个部分,分别为:

   - Type-check expression
   - Type-check variable subscript, and fields
   - Type-check Declaration 


### 类功能说明

| 类名       | 作用                                     |
| -------- | -------------------------------------- |
| Env      | 打印错误,初始化Tenv和Venv                      |
| Tenv     | Type Environment记录type                 |
| Venv     | Value Environment记录Function以及Variables |
| FunEntry | Function的信息类                           |
| VarEntry | Var的信息类                                |
| Semant   | 唯一可以被调用的公共类,用于语义分析,包括类型检查和填充符号表        |



## 实验步骤

1. 新建`Intellij IDEA` 工程文件,按照下列文件结构整合虎书框架`chap5`以及`Parsing`项目的文件.


   ```
.
├── Absyn [34 entries exceeds filelimit, not opening dir]
├── ErrorMsg
│   └── ErrorMsg.java
├── java_cup [66 entries exceeds filelimit, not opening dir]
├── Parse
│   ├── Grm.java
│   ├── Lexer.java
│   ├── Main.java
│   ├── Parse.java
│   ├── sym.java
│   └── Yylex.java
├── Symbol
│   ├── Symbol.java
│   └── Table.java
└── Types
    ├── ARRAY.java
    ├── INT.java
    ├── NAME.java
    ├── NIL.java
    ├── RECORD.java
    ├── STRING.java
    ├── Type.java
    └── VOID.java
   ```

2. 按照虎书`mcij_chapt05c.pdf`要求, 创建`Semant Package` ,并实现如下的目录结构:

   ```
   Semant
   ├── Entry.java
   ├── Env.java
   ├── ExpTy.java
   ├── FunEntry.java
   ├── Semant.java
   └── VarEntry.java
   ```

   类依赖图:

   ![Selection_001](Resource/Selection_001.jpg)

   在实现的过程中需要创建`Translate Package`新增`Exp.java`类,但是由于目前阶段不要求进行中间代码翻译,所以只需要提供一个空类即可,在`Semant.java`中采用null代替所有`Exp.java`类实例.

   ```java
   //Exp.java实现
   package Translate;
   public class Exp { }
   ```

3. 适当更改`Parse/Main` ,将抽象语法输入至语义分析中.



##  实验细节

### Env.java

类结构

![Selection_007](Resource/Selection_007.jpg) 

Env 内部:

| 属性       | 作用                 |
| -------- | ------------------ |
| tenv     | 记录分析过程中的Type声明     |
| venv     | 记录分析过程中的变量声明以及函数申明 |
| errorMsg | 记录分析过程中的发现的错误      |

| 函数                       | 作用                                       |
| ------------------------ | ---------------------------------------- |
| Env(ErrorMsg)            | 类的构造函数. `ErrorMsg`用于分析语义分析过程中的报错.        |
| InitTenv()               | 初始化Tenv, 主要需要添加预置的`int`和`sting` 两个type   |
| InitVenv()               | 初始化Venv,主要需要添加预置的函数,如`print()`,`flush()` 等 |
| AddSystemFunction (args) | 工具函数,添加函数至`Venv`                         |

## 实验结果

## 实验总结

