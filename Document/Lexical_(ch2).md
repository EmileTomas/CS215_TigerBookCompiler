### 词法分析(chapter 2)

### 实验目的

​	使用`Jflex`工具生成符合`Tiger Language`的词法分析器,并在给出的框架下进行词法分析,输出Token流.

### 实验说明

​	本实验主要难点在于`Jflex`的使用,以及`lex`文件的编写, 由于虎书成书久远,所以在使用虎书框架提供的`make`文件时会出现一些Bug. 

​	通过下载官网最新版`Jflex 1.6.1` 并将相应`jar`包添加至系统路径,便可以使用组新的`Jflex`进行词法生成. 同时对`make`文件根据`Jfex`版本变化细微调整一下指令.修改后的`make`文件如下

``` sh
JFLAGS=-g

Parse/Main.class: Parse/*.java Parse/Yylex.java
	javac ${JFLAGS} Parse/*.java

Parse/Yylex.java: Parse/Tiger.lex
	cd Parse; java jflex.Main Tiger.lex;

ErrorMsg/ErrorMsg.class:  ErrorMsg/*.java
	javac ${JFLAGS} ErrorMsg/*.java

clean:
	rm Parse/*.class ErrorMsg/*.class Parse/Yylex.java
```

​	同时为了更加方便的进行分析,本实验以及之后的实验统一使用`Intellij IDEA`作为开发环境. 如有需要,可以前往官网进行[下载](https://www.jetbrains.com/idea/).

### 实验步骤

1. 新建`Intellij IDEA`工程文件,并按照下列文件结构在`ProjectPath/src`文件夹下导入虎书框架`chap2`内容.

   ```
   .
   ├── ErrorMsg
   │   ├── ErrorMsg.class
   │   ├── ErrorMsg.java
   │   └── LineList.class
   ├── java_cup
   │   └── runtime
   ├── Parse
   │   ├── Lexer.java
   │   ├── Main.java
   │   ├── sym.java
   │   ├── Tiger.lex
   │   └── Yylex.java
   └── makefile
   ```

2. 阅读`Tiger Language Reference Mannual.pdf` 文件了解`Tiger Language`的语法结构,主要阅读**Identifiers, Comments, Interger literal,String literal**

3. 结合`MCIJ_chapt02c.pdf`文件中`PROGRAM`部分的要求填充`Tiger.lex`文件.

4. 在项目目录下使用`make`指令生成相关文件(主要为了生成`Yylex.java`文件).或者在`bash`中输入 `java jflex.Main`使用`Jflex`的GUI命令生成`Yylex.java`.![img](file:///home/emile/Documents/Github/TigerCompiler/Document/Resource/1.jpg?lastModify=1480255054)

5. 使用`Intellij Idea`创建相关项目,按照pdf中`PROGRAM`部分导入相关文件,添加输入文件参数进行测试.

### 实验结果

样例:

对于如下输入

```
/* arrays */
let 
	type a = array of int

	var arr1:a := a [10] of 0
in
	arr1[2] := 100
end
```

输出为(为了美观,修改了一下输出的格式):

```
LET 13	TYPE 19	ID 24	EQ 26	ARRAY 28	OF 34	ID 37	VAR 43	ID 47	COLON 51	ID 52	ASSIGN 54	ID 57	LBRACK 59	INT 60	RBRACK 62	OF 64	INT 67	IN 69	ID 73	LBRACK 77	INT 78	RBRACK 79	ASSIGN 81	INT 84	END 88	EOF 92	
```

错误提示样例

```
 arrays */
let 
	type a = array of int

	var arr1:a := a [10] of 0
in
	arr1[2] := 100
end
```

输出:

```
ID 1	
FilePath/17.tig::1.9: Unmatched Comment!
LET 11	TYPE 17	ID 22	EQ 24	ARRAY 26	OF 32	ID 35	VAR 41	ID 45	COLON 49	ID 50	ASSIGN 52	ID 55	LBRACK 57	INT 58	RBRACK 60	OF 62	INT 65	IN 67	ID 71	LBRACK 75	INT 76	RBRACK 77	ASSIGN 79	INT 82	END 86	EOF 90	

```

### 实验总结

在lex文件编写过程中,通过测试主要遇到的问题在于多重嵌套评论的问题,需要使用\<state\>语法才能解决,其余主要的时间还是在于`Jflex`语法的熟悉以及文档的阅读上.