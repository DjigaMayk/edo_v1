package facsimileTest;

public final class TestJsonStrings {
    public static final String IMAGE_CHECKED = "src\\test\\resources\\imagesForFacsimileTest\\MatchFile.jpg";
    public static final String IMAGE_UNCHECKED = "src\\test\\resources\\imagesForFacsimileTest\\UnmatchedFile.jpg";
    public static final String STRING_FOR_ENTITY = """
            {
              "employee" : {
                "id":1
              },
              "department" : {
                "id":1
              },
              "file_pool" : {
                "id":1
              }
            }
            """;
    public static final String STRING_FOR_ARCHIVE = """
            {
                "facsimile":{
                    "id":1,
                    "archived":true
                }
            }
            """;
    public static final String STRING_FOR_UNARCHIVE = """
            {
                "facsimile":{
                    "id":1,
                    "archived":false
                }
            }""";
}
