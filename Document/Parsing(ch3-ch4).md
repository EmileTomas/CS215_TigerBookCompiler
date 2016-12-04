## 抽象语法(chapter3-4)

###	实验目的

对词法分析生成的Token流,利用`CUP`进行语法检查(chapter 3)以及抽象语法的生成(chapter 4)

### 实验说明

本实验主要使用`CUP`完成语法分析,重点在于`Grm.cup`文件的编写,需要仔细阅读`Tiger Language Reference Mannual.pdf`,对于`Expression`部分中每一个生成式进行在`Grm.cup`中完成相应的编写.

### 实验步骤

1. 新建`Intellij IDEA`工程文件,并按照下列文件结构整合虎书框架`chap3`以及`chap4`目录

   ```
   .
   ├── out
   │   └── production
   ├── src
   │   ├── Absyn
   │   ├── ErrorMsg
   │   ├── java_cup
   │   ├── Parse
   │   ├── Symbol
   │   └── Grm.cup
   └── Parsing.iml
   ```

2. 针对`Tiger Language Reference Mannual.pdf`中的第二部分`Expression`的生成式补充`Grm.cup`

3. 使用`bash`进入src目录,输入`java java_cup.Main -parser Grm -expect 3 -dump_grammar -dump_states <Grm.cup >Grm.out 2>Grm.err`生成 `Grm.err` `Grm.java` `Grm.out` `sym.java`文件.需要确保安装了`java`并将`java`添加至系统路径. 如果编译失败,可以打开`Grm.err`文件查找错误原因.

4. 将`Grm.java` 以及`sys.java`移动到`Parse`目录下. `Grm.java`提供了一个`parser`以分析程序语法.

5. 在`Parse.java`文件中导入并使用`Print.java`打印抽象语法结果.

### 细节实现

#### Grm.cup的更改

在`Grm.cup` 文件中添加` Exp ParseResult`进行最终结果的保存. 同时代码中的`Lexer lexer` 用于耦合词法分析器和语法分析器.

```java
parser code {:
	public Exp parseResult;
	Lexer lexer;
	...
    ...
	public Grm(Lexer l, ErrorMsg.ErrorMsg err) {
		this((Scanner)l);
		errorMsg=err;
		lexer=l;
	}
:};
```

之后根据附录补充`Grammar`部分.

```
start with program;
program ::=  expr:e {: parser.parseResult = (Exp)e; :}
```

当语法分析结束时,得到的结果会存放在`parser`的`parseResult`属性属性中

#### 输出抽象语法

```java
import Absyn.Print;

public class Parse {

    public ErrorMsg.ErrorMsg errorMsg;
    public Absyn.Exp absyn;

    public Parse(String filename) {
        errorMsg = new ErrorMsg.ErrorMsg(filename);
        {
            java.io.InputStream inp;
            try {
                inp = new java.io.FileInputStream(filename);
            } catch (java.io.FileNotFoundException e) {
                throw new Error("File not found: " + filename);
            }
            Grm parser = new Grm(new Yylex(inp, errorMsg), errorMsg);
      /* open input files, etc. here */

            try {
                parser./*debug_*/parse();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new Error(e.toString());
            } finally {
                try {
                    inp.close();
                } catch (java.io.IOException e) {
                }
            }
            absyn = parser.parseResult;
            new Print(System.out).prExp(absyn, 0);
        }
    }
}
```

语法分析主要在虎书框架的`Parse`类中给出:

`inp` 				文件输入

`Grm parser = new Grm(new Yylex(inp, errorMsg), errorMsg);`	可以看到`Parser`与`Yylex`是耦合在一起的,`Parser`通过不断调用`Yylex`的`nextToken()`来获得下一个Token.

`parser.parse() `		启动语法分析

`absyn`				输出的语法分析结果

通过`import Absyn.Print`我们可以使用`Print`输出语法分析结果.

### 实验结果

#### 正确输入

输入

```
/* empty let, embedded comment */
let
	var x:int := 0
in
	/*an empty let does not return a value */
end

```

输出

```
LetExp(
 DecList(
 	VarDec(x,int,IntExp(0),true),
 	DecList()),
 SeqExp(ExpLis())
)
```

#### 错误输入

输入

```
/* empty let, embedded comment */
let
	var x:int := 0
in
in	/*error here, dismatch*/
	/*an empty let does not return a value */
end

```

输出

```
Couldn't repair and continue parse at character 57 of input
/home/emile/Documents/Github/TigerCompiler/TestCase/More/Good/10.tig::4.3: Syntax error (IN)
```

语法分析成功输出了一个dismatch error.

### 实验总结

文法编写过程中,主要还是需要比较耐心的对比附录进行实现.同时如何对`Grm.cup`文件进行编译也研究了一段时间,最后还是看官方文档发现如何使用的.通过这次实验对于Java的类文件以及词法分析和语法分析之间如何进行耦合有了更深的理解.