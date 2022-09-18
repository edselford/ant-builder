# Ant Project Builder
Create build.xml for ant project on terminal

### Build
```bash
$ ant compile
$ ant dist    #for jar file
```

### Create `build.xml`
```bash
$ java -jar ant-builder.jar [PROJECT NAME] [DESCRIPTION]
# or
$ java antbuilder.Main [PROJECT NAME] [DESCRIPTION]
```
if you want to use it in all directories, add this to `~/.bashrc` or `~/.zshrc` or `~/.config/fish/config.fish`
```bash
alias antbuilder='java -jar <your-antbuilder-dir>/dist/lib/antbuilder.jar'
```
or if you use Windows, add antbuilder.jar path to environment variables

then, you can use:
```bash
antbuilder [PROJECT NAME] [DESCRIPTION]
```

### Run
```bash
$ ant init    # initialize project
$ ant compile # compile project
$ ant dist    # build jar file
$ ant clean   # remove out/ and dist/ folder
```

## Requirement
- Apache Ant version 1.10.12+ (January 21 1970)
- Java Development Kit (JDK) 17+
