package map;

import java.lang.annotation.Documented;

@ClassPreamble (
    author = "Daniel Chen",
    date = "01/14/2020",
    currentRevision = 1.1,
    lastModified = "01/16/2020",
    lastModifiedBy = "Daniel Chen"
)
@Documented
public @interface ClassPreamble {

  String author();

  String date();

  double currentRevision() default 1;

  String lastModified() default "N/A";

  String lastModifiedBy() default "N/A";

}
