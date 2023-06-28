package resolutionNotificationTest;

public final class TestJsonStrings {
    public static final String SINGLE_RESOLUTION = """
            {
              "id": 1,
              "creatio  nDate": "2023-06-19T09:09:24.334Z",
              "archivedDate": "2023-06-19T09:09:24.334Z",
              "lastActionDate": "2023-06-19T09:09:24.334Z",
              "enumResolution": "RESOLUTION",
              "creator": {
                "id": 1,   \s
                "notification": [
                  {       \s
                    "employee" : {
                        "id":1
                        },
                    "enumNotification": "EMAIL"
                  }
                ]
              },
              "signer": {
                "id": 1,   \s
                "notification": [
                  {       \s
                    "employee" : {
                        "id":1
                        },
                    "enumNotification": "EMAIL"
                  }
                ]
              },
              "executors": [
                {
                    "id": 2,       \s
                    "notification": [
                        {           \s
                        "employee" : {
                            "id":2
                        },
                        "enumNotification": "EMAIL"
                        }
                    ]
                }
              ],
              "curator": {
                "id": 3,
                "notification": [
                  {       \s
                    "employee" : {
                        "id":3
                        },
                    "enumNotification": "EMAIL"
                  }
                ]
              },
              "question": {
                "id": 1,
                "creationDate": "2023-06-19T09:09:24.334Z",
                "archivedDate": "2023-06-19T09:09:24.334Z",
                "summary": "summary",
                "resolutions": [
                 \s
                ],
                "theme": {
                  "id": 6,
                  "code": "1.3",
                  "parentThemeDto": {
                        "id":1
                        }
                }
              }
            }""";

    private TestJsonStrings() {
    }
}
