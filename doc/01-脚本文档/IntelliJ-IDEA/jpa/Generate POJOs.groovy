import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

config = [
        impSerializable  : true,
        extendBaseEntity : false,
        extendBaseService: true

]
baseEntityPackage = "com.hhsj.base.BaseEntity"
baseServicePackage = "com.hhsj.base.BaseService"
baseEntityProperties = ["id", "createDate", "lastModifiedDate", "version"]
typeMapping = [
        (~/(?i)bool|boolean|tinyint/)     : "Boolean",
        (~/(?i)bigint/)                   : "Long",
        (~/int/)                          : "Integer",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "java.util.Date",
        (~/(?i)date/)                     : "java.util.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)number/)                   : "Integer",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
  SELECTION.filter {
    it instanceof DasTable && it.getKind() == ObjectKind.TABLE
  }.each {
    generate(it, dir)
  }
}
//dif -> E:\DEVELOPE\Code\jpademo\src\main\java\com\demo\jpa
def generate(table, dir) {

  def entityPath = "${dir.toString()}\\entity",
      servicePath = "${dir.toString()}\\service",
      repPath = "${dir.toString()}\\repository",
      serviceImpPath = "${dir.toString()}\\service\\impl"

  mkdirs([entityPath, servicePath, repPath, serviceImpPath])

  def entityName = javaName(table.getName(), true)
  def fields = calcFields(table)
  def basePackage = clacBasePackage(dir)

  new File("${entityPath}\\${entityName}.java").withPrintWriter { out -> genEntity(out, table, entityName, fields, basePackage) }
  new File("${servicePath}\\${entityName}Service.java").withPrintWriter { out -> genService(out, table, entityName, fields, basePackage) }
  new File("${repPath}\\${entityName}Repository.java").withPrintWriter { out -> genRepository(out, table, entityName, fields, basePackage) }
//  new File("${repPath}\\${entityName}RepositoryCustom.java").withPrintWriter { out -> genRepositoryCustom(out, entityName, basePackage) }
  new File("${serviceImpPath}\\${entityName}ServiceImpl.java").withPrintWriter { out -> getServiceImpl(out, table, entityName, fields, basePackage) }

}

def genProperty(out, field) {
  if (field.annos != "") out.println "  ${field.annos}"
  if (field.colum != field.name) {
    out.println "\t@Column(name = \"${field.colum}\")"
  }
  out.println "\tprivate ${field.type} ${field.name};"
  out.println ""
}

def genEntity(out, table, entityName, fields, basePackage) {
//  out.println "package ${basePackage}.entity;"
  out.println ""
  if (config.extendBaseEntity) {
    out.println "import $baseEntityPackage;"
  }
  out.println "import lombok.Data;"
  out.println ""
  if (config.impSerializable) {
    out.println "import java.io.Serializable;"
    out.println "import com.fasterxml.jackson.annotation.JsonIgnoreProperties;"
    out.println ""
  }
  out.println "import javax.persistence.*;"
  out.println ""
  out.println "@Data"
  out.println "@AllArgsConstructor"
  out.println "@NoArgsConstructor"
  out.println "@Entity"
  out.println "@Table(name = \"${table.getName()}\")"
  out.println "@JsonIgnoreProperties(value={\"hibernateLazyInitializer\",\"handler\",\"fieldHandler\"})"
  out.println "public class $entityName${config.extendBaseEntity ? " extends BaseEntity" : ""}${config.impSerializable ? " implements Serializable" : ""} {"
  out.println ""
  if (config.extendBaseEntity) {
    // 继承父类
    fields.each() { if (!isBaseEntityProperty(it.name)) genProperty(out, it) }
  } else {
    // 不继承父类
    out.println "\t@Id"
    out.println "\t@GeneratedValue(strategy = GenerationType.IDENTITY)"
    fields.each() { genProperty(out, it) }
  }
  out.println "}"

}

def genService(out, table, entityName, fields, basePackage) {
//  out.println "package ${basePackage}.service;"
  out.println ""
//  out.println "import ${basePackage}.repository.${entityName}Repository;"
//  out.println "import ${basePackage}.base.Result;"
  if (config.extendBaseService) {
//    out.println "import $baseServicePackage;"
//    out.println "import ${basePackage}.entity.$entityName;"
  }
  out.println "import org.springframework.stereotype.Service;"
  out.println ""
  out.println "import javax.annotation.Resource;"
  out.println ""
  out.println "public interface ${entityName}Service${config.extendBaseService ? " extends BaseService<$entityName, ${fields[0].type}>" : ""}  {"
  out.println ""
  out.println "   //根据id查询"
  out.println "   public Result getOne(${fields[0].type} id);"
  out.println ""
  out.println "   //根据id删除"
  out.println "   public Result deleteOne(${fields[0].type} id);"
  out.println ""
  out.println "   //根据id保存"
  out.println "   public Result saveOne(${entityName} entity);"
  out.println "}"
}

def genRepository(out, table, entityName, fields, basePackage) {
//  out.println "package ${basePackage}.repository;"
  out.println ""
//  out.println "import ${basePackage}.entity.$entityName;"
  out.println "import org.springframework.data.jpa.repository.JpaRepository;"
  out.println "import org.springframework.stereotype.Repository;"
  out.println ""
  out.println "@Repository"
  out.println "public interface ${entityName}Repository extends BaseRepository<$entityName, ${fields[0].type}>{"
  out.println "    "
  out.println "}"
}

/*def genRepositoryCustom(out, entityName, basePackage) {
  out.println "package ${basePackage}.repository;"
  out.println ""
  out.println "public interface ${entityName}RepositoryCustom { "
  out.println "}"
}*/

def getServiceImpl(out, table, entityName, fields, basePackage) {
//  out.println "package ${basePackage}.service.impl;"
  out.println ""
//  out.println "import ${basePackage}.service.${entityName}Service;"
  out.println "import org.springframework.stereotype.Service;"
//  out.println "import ${basePackage}.base.Result;"
//  out.println "import ${basePackage}.entity.${entityName};"
//  out.println "import ${basePackage}.service.${entityName}Service;"
//  out.println "import ${basePackage}.repository.${entityName}Repository;"
  out.println "import org.springframework.beans.factory.annotation.Autowired;"
  out.println ""
  out.println "@Service"
  out.println "public class ${entityName}ServiceImpl implements ${entityName}Service {"
  out.println ""
  out.println "\t@Autowired"
  out.println "\tprivate ${entityName}Repository rep;"
  out.println ""
  out.println "\t@Override"
  out.println "\tpublic Result getOne(${fields[0].type} id){"
  out.println " \t\t${entityName} one = rep.getOne(id);"
  out.println "\t\treturn Result.builder().success().data(one).build();"
  out.println "\t}"
  out.println ""
  out.println "\t@Override"
  out.println "\tpublic Result deleteOne(${fields[0].type} id){"
  out.println " \t\trep.deleteById(id);"
  out.println "\t\treturn Result.builder().success().build();"
  out.println "\t}"
  out.println ""
  out.println "\t@Override"
  out.println "\tpublic Result saveOne(${entityName} entity){"
  out.println " \t\t${entityName} one = rep.save(entity);"
  out.println "\t\treturn Result.builder().success().data(one).build();"
  out.println "\t}"
  out.println "}"
}

def mkdirs(dirs) {
  dirs.forEach {
    def f = new File(it)
    if (!f.exists()) {
      f.mkdirs();
    }
  }
}

def clacBasePackage(dir) {
  dir.toString()
          .replaceAll("^.+\\\\src\\\\main\\\\java\\\\", "")
          .replaceAll("\\\\", ".")
}

def isBaseEntityProperty(property) {
  baseEntityProperties.find { it == property } != null
}
// 转换类型
def calcFields(table) {
  DasUtil.getColumns(table).reduce([]) {
    fields, col ->
      def spec = Case.LOWER.apply(col.getDataType().getSpecification())
      def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
      fields += [[
                         name : javaName(col.getName(), false),
                         colum: col.getName(),
                         type : typeStr,
                         annos: ""]]
  }
}

def javaName(str, capitalize) {
  def s = str.split(/(?<=[^\p{IsLetter}])/).collect { Case.LOWER.apply(it).capitalize() }
          .join("").replaceAll(/[^\p{javaJavaIdentifierPart}]/, "_").replaceAll(/_/, "")
  capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
