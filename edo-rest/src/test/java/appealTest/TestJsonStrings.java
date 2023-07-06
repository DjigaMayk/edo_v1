package appealTest;

public final class TestJsonStrings {
    public static final String SINGLE_APPEAL = """
            {
               "id": 1,
               "number": "123",
               "annotation": "some annotation",
               "signer": [
                 {
                   "id": 1
                 }
               ],
               "creator": {
                 "id": 2
               },
               "addressee": [
                 {
                   "id": 3
                 }
               ],
               "nomenclature": {
                 "id": 3,
                 "creationDate": "2023-07-06T07:52:10.593Z",
                 "archivedDate": "2023-07-06T07:52:10.593Z",
                 "template": "%ЧИС%ГОД-%ЗНАЧ/2",
                 "currentValue": 0,
                 "index": "666"
               },
               "authors": [
                 {
                     "id": 4,
                     "firstName": "Дмитрий",
                     "lastName": "Рейн",
                     "middleName": "Константинович",
                     "address": "Address",
                     "snils": "88012234682",
                     "mobilePhone": "79776020338",
                     "email": "room083@gmail.com",
                     "employment": "UNEMPLOYED",
                     "fioDative": "Рейну Дмитрию Константиновичу",
                     "fioGenitive": "Рейна Дмитрия Константиновича",
                     "fioNominative": "Дмитрий Константинович Рейн"
                 }
               ],
               "file": [
                 {
                   "id": 1,
                   "storageFileId": "0fd52378-819e-47f3-bd58-293edd25ed0a",
                   "name": "cat",
                   "extension": "jpg",
                   "size": 1,
                   "pageCount": 1,
                   "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                   "archivedDate": null,
                   "creator": {
                     "id": 1
                   }
                 }
               ],
               "question": [{
                   "id": 1,
                   "summary": "осы вынесли окно",
                   "theme": {
                     "id": 6,
                     "code": "1.3",
                     "parentThemeDto": {
                             "id":1
                             }
                     }
               }],
               "appealStatus": null,
               "sendingMethod": "BY_EMAIL",
               "answeringMethod": "BY_EMAIL",
               "region": {
                 "id": 1,
                 "externalId": "174",
                 "regionName": "Челябинск",
                 "archivedDate": "2023-07-06T07:56:00.763Z",
                 "quantity": "5",
                 "federalDistrict": {
                   "id": 1
                 },
                 "numberOfPrimaryBranches": "3",
                 "numberOfLocalBranches": "3"
               }
             }""";
    public static final String CHANGED_SINGLE_APPEAL = """
            {
               "id": 1,
               "number": "321",
               "annotation": "some new annotation",
               "signer": [
                 {
                   "id": 1
                 }
               ],
               "creator": {
                 "id": 2
               },
               "addressee": [
                 {
                   "id": 3
                 }
               ],
               "nomenclature": {
                 "id": 3,
                 "creationDate": "2023-07-06T07:52:10.593Z",
                 "archivedDate": "2023-07-06T07:52:10.593Z",
                 "template": "%ЧИС%ГОД-%ЗНАЧ/2",
                 "currentValue": 0,
                 "index": "666"
               },
               "authors": [
                 {
                     "id": 4,
                     "firstName": "Дмитрий",
                     "lastName": "Рейн",
                     "middleName": "Константинович",
                     "address": "Address",
                     "snils": "88012234682",
                     "mobilePhone": "79776020338",
                     "email": "room083@gmail.com",
                     "employment": "UNEMPLOYED",
                     "fioDative": "Рейну Дмитрию Константиновичу",
                     "fioGenitive": "Рейна Дмитрия Константиновича",
                     "fioNominative": "Дмитрий Константинович Рейн"
                 }
               ],
               "file": [
                 {
                   "id": 1,
                   "storageFileId": "0fd52378-819e-47f3-bd58-293edd25ed0a",
                   "name": "cat",
                   "extension": "jpg",
                   "size": 1,
                   "pageCount": 1,
                   "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                   "archivedDate": null,
                   "creator": {
                     "id": 1
                   }
                 }
               ],
               "question": [{
                   "id": 1,
                   "summary": "осы вынесли окно",
                   "theme": {
                     "id": 6,
                     "code": "1.3",
                     "parentThemeDto": {
                             "id":1
                             }
                     }
               }],
               "appealStatus": null,
               "sendingMethod": "BY_EMAIL",
               "answeringMethod": "BY_EMAIL",
               "region": {
                 "id": 1,
                 "externalId": "174",
                 "regionName": "Челябинск",
                 "archivedDate": "2023-07-06T07:56:00.763Z",
                 "quantity": "5",
                 "federalDistrict": {
                   "id": 1
                 },
                 "numberOfPrimaryBranches": "3",
                 "numberOfLocalBranches": "3"
               }
             }""";
    public static final String SINGLE_APPEAL_WITH_NOT_EXISTS_ID = """
            {
               "id": 30981237718392089,
               "number": "321",
               "annotation": "some new annotation",
               "signer": [
                 {
                   "id": 1
                 }
               ],
               "creator": {
                 "id": 2
               },
               "addressee": [
                 {
                   "id": 3
                 }
               ],
               "nomenclature": {
                 "id": 3,
                 "creationDate": "2023-07-06T07:52:10.593Z",
                 "archivedDate": "2023-07-06T07:52:10.593Z",
                 "template": "%ЧИС%ГОД-%ЗНАЧ/2",
                 "currentValue": 0,
                 "index": "666"
               },
               "authors": [
                 {
                     "id": 4,
                     "firstName": "Дмитрий",
                     "lastName": "Рейн",
                     "middleName": "Константинович",
                     "address": "Address",
                     "snils": "88012234682",
                     "mobilePhone": "79776020338",
                     "email": "room083@gmail.com",
                     "employment": "UNEMPLOYED",
                     "fioDative": "Рейну Дмитрию Константиновичу",
                     "fioGenitive": "Рейна Дмитрия Константиновича",
                     "fioNominative": "Дмитрий Константинович Рейн"
                 }
               ],
               "file": [
                 {
                   "id": 1,
                   "storageFileId": "0fd52378-819e-47f3-bd58-293edd25ed0a",
                   "name": "cat",
                   "extension": "jpg",
                   "size": 1,
                   "pageCount": 1,
                   "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                   "archivedDate": null,
                   "creator": {
                     "id": 1
                   }
                 }
               ],
               "question": [{
                   "id": 1,
                   "summary": "осы вынесли окно",
                   "theme": {
                     "id": 6,
                     "code": "1.3",
                     "parentThemeDto": {
                             "id":1
                             }
                     }
               }],
               "appealStatus": null,
               "sendingMethod": "BY_EMAIL",
               "answeringMethod": "BY_EMAIL",
               "region": {
                 "id": 1,
                 "externalId": "174",
                 "regionName": "Челябинск",
                 "archivedDate": "2023-07-06T07:56:00.763Z",
                 "quantity": "5",
                 "federalDistrict": {
                   "id": 1
                 },
                 "numberOfPrimaryBranches": "3",
                 "numberOfLocalBranches": "3"
               }
             }""";
    public static final String MULTIPLE_APPEALS = """
            [""
                        {
                            "id": null,
                            "creationDate": null,
                            "archivedDate": null,
                            "number": "123",
                            "annotation": "Some annotation",
                            "signer": [{
                                "id": null,
                                "firstName": "Дмитрий",
                                "lastName": "Рейн",
                                "middleName": "Константинович",
                                "address": "Address",
                                "photoUrl": null,
                                "fioDative": "Рейну Дмитрию Константиновичу",
                                "fioNominative": "Дмитрий Константинович Рейн",
                                "fioGenitive": "Рейна Дмитрия Константиновича",
                                "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                "phone": "+79776020338",
                                "workPhone": "+79786039037",
                                "workEmail": "room083@gmail.com",
                                "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                "username": "reyn_d",
                                "creationDate": null,
                                "archivedDate": null,
                                "notification": null
                            }],
                            "creator": {
                                "id": null,
                                "firstName": "Дмитрий",
                                "lastName": "Рейн",
                                "middleName": "Константинович",
                                "address": "Address",
                                "photoUrl": null,
                                "fioDative": "Рейну Дмитрию Константиновичу",
                                "fioNominative": "Дмитрий Константинович Рейн",
                                "fioGenitive": "Рейна Дмитрия Константиновича",
                                "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                "phone": "+79776020338",
                                "workPhone": "+79786039037",
                                "workEmail": "room083@gmail.com",
                                "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                "username": "reyn_d",
                                "creationDate": null,
                                "archivedDate": null,
                                "notification": null
                            },
                            "addressee": [{
                                "id": null,
                                "firstName": "Дмитрий",
                                "lastName": "Рейн",
                                "middleName": "Константинович",
                                "address": "Address",
                                "photoUrl": null,
                                "fioDative": "Рейну Дмитрию Константиновичу",
                                "fioNominative": "Дмитрий Константинович Рейн",
                                "fioGenitive": "Рейна Дмитрия Константиновича",
                                "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                "phone": "+79776020338",
                                "workPhone": "+79786039037",
                                "workEmail": "room083@gmail.com",
                                "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                "username": "reyn_d",
                                "creationDate": null,
                                "archivedDate": null,
                                "notification": null
                            }],
                            "nomenclature": null,
                            "authors": [{
                                "id": null,
                                "firstName": "Дмитрий",
                                "lastName": "Рейн",
                                "middleName": "Константинович",
                                "address": "Address",
                                "snils": "88012234682",
                                "mobilePhone": "+79776020338",
                                "email": "room083@gmail.com",
                                "employment": "UNEMPLOYED",
                                "fioDative": "Рейну Дмитрию Константиновичу",
                                "fioGenitive": "Рейна Дмитрия Константиновича",
                                "fioNominative": "Дмитрий Константинович Рейн"
                            }],
                            "file": [{
                                "id": null,
                                "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                                "name": "some_name",
                                "extension": "txt",
                                "size": 12334,
                                "pageCount": 12,
                                "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                                "archivedDate": null,
                                "creator": {
                                    "id": null,
                                    "firstName": "Дмитрий",
                                    "lastName": "Рейн",
                                    "middleName": "Константинович",
                                    "address": "Address",
                                    "photoUrl": null,
                                    "fioDative": "Рейну Дмитрию Константиновичу",
                                    "fioNominative": "Дмитрий Константинович Рейн",
                                    "fioGenitive": "Рейна Дмитрия Константиновича",
                                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                    "phone": "+79776020338",
                                    "workPhone": "+79786039037",
                                    "workEmail": "room083@gmail.com",
                                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                    "username": "reyn_d",
                                    "creationDate": null,
                                    "archivedDate": null,
                                    "notification": null
                                }
                            }],
                            "question": [],
                            "appealStatus": null,
                            "sendingMethod": "BY_EMAIL",
                            "answeringMethod": "BY_EMAIL"
                        }"", ""
                                         {
                                             "id": null,
                                             "creationDate": null,
                                             "archivedDate": null,
                                             "number": "123",
                                             "annotation": "Some annotation",
                                             "signer": [{
                                                 "id": null,
                                                 "firstName": "Дмитрий",
                                                 "lastName": "Рейн",
                                                 "middleName": "Константинович",
                                                 "address": "Address",
                                                 "photoUrl": null,
                                                 "fioDative": "Рейну Дмитрию Константиновичу",
                                                 "fioNominative": "Дмитрий Константинович Рейн",
                                                 "fioGenitive": "Рейна Дмитрия Константиновича",
                                                 "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                                 "phone": "+79776020338",
                                                 "workPhone": "+79786039037",
                                                 "workEmail": "room083@gmail.com",
                                                 "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                                 "username": "reyn_d",
                                                 "creationDate": null,
                                                 "archivedDate": null,
                                                 "notification": null
                                             }],
                                             "creator": {
                                                 "id": null,
                                                 "firstName": "Дмитрий",
                                                 "lastName": "Рейн",
                                                 "middleName": "Константинович",
                                                 "address": "Address",
                                                 "photoUrl": null,
                                                 "fioDative": "Рейну Дмитрию Константиновичу",
                                                 "fioNominative": "Дмитрий Константинович Рейн",
                                                 "fioGenitive": "Рейна Дмитрия Константиновича",
                                                 "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                                 "phone": "+79776020338",
                                                 "workPhone": "+79786039037",
                                                 "workEmail": "room083@gmail.com",
                                                 "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                                 "username": "reyn_d",
                                                 "creationDate": null,
                                                 "archivedDate": null,
                                                 "notification": null
                                             },
                                             "addressee": [{
                                                 "id": null,
                                                 "firstName": "Дмитрий",
                                                 "lastName": "Рейн",
                                                 "middleName": "Константинович",
                                                 "address": "Address",
                                                 "photoUrl": null,
                                                 "fioDative": "Рейну Дмитрию Константиновичу",
                                                 "fioNominative": "Дмитрий Константинович Рейн",
                                                 "fioGenitive": "Рейна Дмитрия Константиновича",
                                                 "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                                 "phone": "+79776020338",
                                                 "workPhone": "+79786039037",
                                                 "workEmail": "room083@gmail.com",
                                                 "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                                 "username": "reyn_d",
                                                 "creationDate": null,
                                                 "archivedDate": null,
                                                 "notification": null
                                             }],
                                             "nomenclature": null,
                                             "authors": [{
                                                 "id": null,
                                                 "firstName": "Дмитрий",
                                                 "lastName": "Рейн",
                                                 "middleName": "Константинович",
                                                 "address": "Address",
                                                 "snils": "88012234682",
                                                 "mobilePhone": "+79776020338",
                                                 "email": "room083@gmail.com",
                                                 "employment": "UNEMPLOYED",
                                                 "fioDative": "Рейну Дмитрию Константиновичу",
                                                 "fioGenitive": "Рейна Дмитрия Константиновича",
                                                 "fioNominative": "Дмитрий Константинович Рейн"
                                             }],
                                             "file": [{
                                                 "id": null,
                                                 "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                                                 "name": "some_name",
                                                 "extension": "txt",
                                                 "size": 12334,
                                                 "pageCount": 12,
                                                 "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                                                 "archivedDate": null,
                                                 "creator": {
                                                     "id": null,
                                                     "firstName": "Дмитрий",
                                                     "lastName": "Рейн",
                                                     "middleName": "Константинович",
                                                     "address": "Address",
                                                     "photoUrl": null,
                                                     "fioDative": "Рейну Дмитрию Константиновичу",
                                                     "fioNominative": "Дмитрий Константинович Рейн",
                                                     "fioGenitive": "Рейна Дмитрия Константиновича",
                                                     "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                                                     "phone": "+79776020338",
                                                     "workPhone": "+79786039037",
                                                     "workEmail": "room083@gmail.com",
                                                     "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                                                     "username": "reyn_d",
                                                     "creationDate": null,
                                                     "archivedDate": null,
                                                     "notification": null
                                                 }
                                             }],
                                             "question": [],
                                             "appealStatus": null,
                                             "sendingMethod": "BY_EMAIL",
                                             "answeringMethod": "BY_EMAIL"
                                         }""]""";
    public static final String APPEAL_WITHOUT_AUTHORS = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_SINGLE_AUTHOR = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_MULTIPLE_AUTHORS = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                },{
                    "id": null,
                    "firstName": "Данил",
                    "lastName": "Белоусов",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_SINGLE_QUESTION = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [{
                               "id": 1,
                               "creationDate": "2022-05-20T10:30:00+00:00",
                               "archivedDate": "2022-05-25T12:45:00+00:00",
                               "summary": "Вопрос по работе",
                               "resolutions": [
                                 {
                                   "id": 1,
                                   "creationDate": "2022-05-21T09:15:00+00:00",
                                   "archivedDate": "2022-05-22T10:30:00+00:00",
                                   "lastActionDate": "2022-05-22T10:00:00+00:00",
                                   "enumResolution": "REQUEST",
                                   "creator": {
                                     "id": 1,
                                     "firstName": "Иван",
                                     "lastName": "Иванов",
                                     "middleName": "Иванович",
                                     "address": "г. Москва, ул. Пушкина, д.10",
                                     "photoUrl": "https://example.com/photos/1",
                                     "fioDative": "Иванову Ивану Ивановичу",
                                     "fioNominative": "Иванов Иван Иванович",
                                     "fioGenitive": "Иванова Ивана Ивановича",
                                     "externalId": "123456789",
                                     "phone": "+7 (123) 456-78-90",
                                     "workPhone": "+7 (123) 456-78-91",
                                     "workEmail": "ivanov@example.com",
                                     "birthDate": "1990-01-01T00:00:00+00:00",
                                     "username": "ivanov",
                                     "creationDate": "2022-01-01T09:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 1,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 2,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "signer": {
                                     "id": 2,
                                     "firstName": "Петр",
                                     "lastName": "Петров",
                                     "middleName": "Петрович",
                                     "address": "г. Москва, ул. Ленина, д.20",
                                     "photoUrl": "https://example.com/photos/2",
                                     "fioDative": "Петрову Петру Петровичу",
                                     "fioNominative": "Петров Петр Петрович",
                                     "fioGenitive": "Петрова Петра Петровича",
                                     "externalId": "987654321",
                                     "phone": "+7 (987) 654-32-10",
                                     "workPhone": "+7 (987) 654-32-11",
                                     "workEmail": "petrov@example.com",
                                     "birthDate": "1995-05-05T00:00:00+00:00",
                                     "username": "petrov",
                                     "creationDate": "2022-02-01T10:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 3,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "executors": [
                                     {
                                       "id": 3,
                                       "firstName": "Алексей",
                                       "lastName": "Алексеев",
                                       "middleName": "Алексеевич",
                                       "address": "г. Москва, ул. Гагарина, д.30",
                                       "photoUrl": "https://example.com/photos/3",
                                       "fioDative": "Алексееву Алексею Алексеевичу",
                                       "fioNominative": "Алексеев Алексей Алексеевич",
                                       "fioGenitive": "Алексеева Алексея Алексеевича",
                                       "externalId": "555555555",
                                       "phone": "+7 (555) 555-55-55",
                                       "workPhone": "+7 (555) 555-55-56",
                                       "workEmail": "alekseev@example.com",
                                       "birthDate": "1992-03-15T00:00:00+00:00",
                                       "username": "alekseev",
                                       "creationDate": "2022-03-01T11:00:00+00:00",
                                       "archivedDate": null,
                                       "notification": [
                                         {
                                           "id": 4,
                                           "enumNotification": "EMAIL"
                                         }
                                       ]
                                     }
                                   ],
                                   "curator": {
                                     "id": 4,
                                     "firstName": "Сергей",
                                     "lastName": "Сергеев",
                                     "middleName": "Сергеевич",
                                     "address": "г. Москва, ул. Сергеева, д.40",
                                     "photoUrl": "https://example.com/photos/4",
                                     "fioDative": "Сергееву Сергею Сергеевичу",
                                     "fioNominative": "Сергеев Сергей Сергеевич",
                                     "fioGenitive": "Сергеева Сергея Сергеевича",
                                     "externalId": "111111111",
                                     "phone": "+7 (111) 111-11-11",
                                     "workPhone": "+7 (111) 111-11-12",
                                     "workEmail": "sergeev@example.com",
                                     "birthDate": "1988-08-08T00:00:00+00:00",
                                     "username": "sergeev",
                                     "creationDate": "2022-04-01T12:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 5,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 6,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "question": {
                                     "id": 1,
                                     "creationDate": "2022-05-20T10:30:00+00:00",
                                     "archivedDate": "2022-05-25T12:45:00+00:00",
                                     "summary": "Вопрос по работе",
                                     "resolutions": null,
                                     "theme": {
                                       "id": 1,
                                       "name": "Рабочие вопросы",
                                       "creationDate": "2022-01-01T09:00:00+00:00",
                                       "archivedDate": null,
                                       "code": "RW",
                                       "parentThemeDto": null
                                     }
                                   }
                                 }
                               ]
                             }
                             ],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_MULTIPLE_QUESTIONS = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [{
                               "id": 1,
                               "creationDate": "2022-05-20T10:30:00+00:00",
                               "archivedDate": "2022-05-25T12:45:00+00:00",
                               "summary": "Вопрос по работе",
                               "resolutions": [
                                 {
                                   "id": 1,
                                   "creationDate": "2022-05-21T09:15:00+00:00",
                                   "archivedDate": "2022-05-22T10:30:00+00:00",
                                   "lastActionDate": "2022-05-22T10:00:00+00:00",
                                   "enumResolution": "REQUEST",
                                   "creator": {
                                     "id": 1,
                                     "firstName": "Иван",
                                     "lastName": "Иванов",
                                     "middleName": "Иванович",
                                     "address": "г. Москва, ул. Пушкина, д.10",
                                     "photoUrl": "https://example.com/photos/1",
                                     "fioDative": "Иванову Ивану Ивановичу",
                                     "fioNominative": "Иванов Иван Иванович",
                                     "fioGenitive": "Иванова Ивана Ивановича",
                                     "externalId": "123456789",
                                     "phone": "+7 (123) 456-78-90",
                                     "workPhone": "+7 (123) 456-78-91",
                                     "workEmail": "ivanov@example.com",
                                     "birthDate": "1990-01-01T00:00:00+00:00",
                                     "username": "ivanov",
                                     "creationDate": "2022-01-01T09:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 1,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 2,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "signer": {
                                     "id": 2,
                                     "firstName": "Петр",
                                     "lastName": "Петров",
                                     "middleName": "Петрович",
                                     "address": "г. Москва, ул. Ленина, д.20",
                                     "photoUrl": "https://example.com/photos/2",
                                     "fioDative": "Петрову Петру Петровичу",
                                     "fioNominative": "Петров Петр Петрович",
                                     "fioGenitive": "Петрова Петра Петровича",
                                     "externalId": "987654321",
                                     "phone": "+7 (987) 654-32-10",
                                     "workPhone": "+7 (987) 654-32-11",
                                     "workEmail": "petrov@example.com",
                                     "birthDate": "1995-05-05T00:00:00+00:00",
                                     "username": "petrov",
                                     "creationDate": "2022-02-01T10:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 3,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "executors": [
                                     {
                                       "id": 3,
                                       "firstName": "Алексей",
                                       "lastName": "Алексеев",
                                       "middleName": "Алексеевич",
                                       "address": "г. Москва, ул. Гагарина, д.30",
                                       "photoUrl": "https://example.com/photos/3",
                                       "fioDative": "Алексееву Алексею Алексеевичу",
                                       "fioNominative": "Алексеев Алексей Алексеевич",
                                       "fioGenitive": "Алексеева Алексея Алексеевича",
                                       "externalId": "555555555",
                                       "phone": "+7 (555) 555-55-55",
                                       "workPhone": "+7 (555) 555-55-56",
                                       "workEmail": "alekseev@example.com",
                                       "birthDate": "1992-03-15T00:00:00+00:00",
                                       "username": "alekseev",
                                       "creationDate": "2022-03-01T11:00:00+00:00",
                                       "archivedDate": null,
                                       "notification": [
                                         {
                                           "id": 4,
                                           "enumNotification": "EMAIL"
                                         }
                                       ]
                                     }
                                   ],
                                   "curator": {
                                     "id": 4,
                                     "firstName": "Сергей",
                                     "lastName": "Сергеев",
                                     "middleName": "Сергеевич",
                                     "address": "г. Москва, ул. Сергеева, д.40",
                                     "photoUrl": "https://example.com/photos/4",
                                     "fioDative": "Сергееву Сергею Сергеевичу",
                                     "fioNominative": "Сергеев Сергей Сергеевич",
                                     "fioGenitive": "Сергеева Сергея Сергеевича",
                                     "externalId": "111111111",
                                     "phone": "+7 (111) 111-11-11",
                                     "workPhone": "+7 (111) 111-11-12",
                                     "workEmail": "sergeev@example.com",
                                     "birthDate": "1988-08-08T00:00:00+00:00",
                                     "username": "sergeev",
                                     "creationDate": "2022-04-01T12:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 5,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 6,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "question": {
                                     "id": 1,
                                     "creationDate": "2022-05-20T10:30:00+00:00",
                                     "archivedDate": "2022-05-25T12:45:00+00:00",
                                     "summary": "Вопрос по работе",
                                     "resolutions": null,
                                     "theme": {
                                       "id": 1,
                                       "name": "Рабочие вопросы",
                                       "creationDate": "2022-01-01T09:00:00+00:00",
                                       "archivedDate": null,
                                       "code": "RW",
                                       "parentThemeDto": null
                                     }
                                   }
                                 }
                               ]
                             },{
                               "id": 1,
                               "creationDate": "2022-05-20T10:30:00+00:00",
                               "archivedDate": "2022-05-25T12:45:00+00:00",
                               "summary": "Вопрос по работе",
                               "resolutions": [
                                 {
                                   "id": 1,
                                   "creationDate": "2022-05-21T09:15:00+00:00",
                                   "archivedDate": "2022-05-22T10:30:00+00:00",
                                   "lastActionDate": "2022-05-22T10:00:00+00:00",
                                   "enumResolution": "REQUEST",
                                   "creator": {
                                     "id": 1,
                                     "firstName": "Иван",
                                     "lastName": "Иванов",
                                     "middleName": "Иванович",
                                     "address": "г. Москва, ул. Пушкина, д.10",
                                     "photoUrl": "https://example.com/photos/1",
                                     "fioDative": "Иванову Ивану Ивановичу",
                                     "fioNominative": "Иванов Иван Иванович",
                                     "fioGenitive": "Иванова Ивана Ивановича",
                                     "externalId": "123456789",
                                     "phone": "+7 (123) 456-78-90",
                                     "workPhone": "+7 (123) 456-78-91",
                                     "workEmail": "ivanov@example.com",
                                     "birthDate": "1990-01-01T00:00:00+00:00",
                                     "username": "ivanov",
                                     "creationDate": "2022-01-01T09:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 1,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 2,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "signer": {
                                     "id": 2,
                                     "firstName": "Петр",
                                     "lastName": "Петров",
                                     "middleName": "Петрович",
                                     "address": "г. Москва, ул. Ленина, д.20",
                                     "photoUrl": "https://example.com/photos/2",
                                     "fioDative": "Петрову Петру Петровичу",
                                     "fioNominative": "Петров Петр Петрович",
                                     "fioGenitive": "Петрова Петра Петровича",
                                     "externalId": "987654321",
                                     "phone": "+7 (987) 654-32-10",
                                     "workPhone": "+7 (987) 654-32-11",
                                     "workEmail": "petrov@example.com",
                                     "birthDate": "1995-05-05T00:00:00+00:00",
                                     "username": "petrov",
                                     "creationDate": "2022-02-01T10:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 3,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "executors": [
                                     {
                                       "id": 3,
                                       "firstName": "Алексей",
                                       "lastName": "Алексеев",
                                       "middleName": "Алексеевич",
                                       "address": "г. Москва, ул. Гагарина, д.30",
                                       "photoUrl": "https://example.com/photos/3",
                                       "fioDative": "Алексееву Алексею Алексеевичу",
                                       "fioNominative": "Алексеев Алексей Алексеевич",
                                       "fioGenitive": "Алексеева Алексея Алексеевича",
                                       "externalId": "555555555",
                                       "phone": "+7 (555) 555-55-55",
                                       "workPhone": "+7 (555) 555-55-56",
                                       "workEmail": "alekseev@example.com",
                                       "birthDate": "1992-03-15T00:00:00+00:00",
                                       "username": "alekseev",
                                       "creationDate": "2022-03-01T11:00:00+00:00",
                                       "archivedDate": null,
                                       "notification": [
                                         {
                                           "id": 4,
                                           "enumNotification": "EMAIL"
                                         }
                                       ]
                                     }
                                   ],
                                   "curator": {
                                     "id": 4,
                                     "firstName": "Сергей",
                                     "lastName": "Сергеев",
                                     "middleName": "Сергеевич",
                                     "address": "г. Москва, ул. Сергеева, д.40",
                                     "photoUrl": "https://example.com/photos/4",
                                     "fioDative": "Сергееву Сергею Сергеевичу",
                                     "fioNominative": "Сергеев Сергей Сергеевич",
                                     "fioGenitive": "Сергеева Сергея Сергеевича",
                                     "externalId": "111111111",
                                     "phone": "+7 (111) 111-11-11",
                                     "workPhone": "+7 (111) 111-11-12",
                                     "workEmail": "sergeev@example.com",
                                     "birthDate": "1988-08-08T00:00:00+00:00",
                                     "username": "sergeev",
                                     "creationDate": "2022-04-01T12:00:00+00:00",
                                     "archivedDate": null,
                                     "notification": [
                                       {
                                         "id": 5,
                                         "enumNotification": "EMAIL"
                                       },
                                       {
                                         "id": 6,
                                         "enumNotification": "EMAIL"
                                       }
                                     ]
                                   },
                                   "question": {
                                     "id": 1,
                                     "creationDate": "2022-05-20T10:30:00+00:00",
                                     "archivedDate": "2022-05-25T12:45:00+00:00",
                                     "summary": "Вопрос по работе",
                                     "resolutions": null,
                                     "theme": {
                                       "id": 1,
                                       "name": "Рабочие вопросы",
                                       "creationDate": "2022-01-01T09:00:00+00:00",
                                       "archivedDate": null,
                                       "code": "RW",
                                       "parentThemeDto": null
                                     }
                                   }
                                 }
                               ]
                             }
                             ],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_FILEPOOL = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITHOUT_FILEPOOL = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_VALID_FIELDS = """
            {
                "id": null,
                "creationDate": null,
                "archivedDate": null,
                "number": "123",
                "annotation": "Some annotation",
                "signer": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "creator": {
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                },
                "addressee": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "photoUrl": null,
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioNominative": "Дмитрий Константинович Рейн",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                    "phone": "+79776020338",
                    "workPhone": "+79786039037",
                    "workEmail": "room083@gmail.com",
                    "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                    "username": "reyn_d",
                    "creationDate": null,
                    "archivedDate": null,
                    "notification": null
                }],
                "nomenclature": null,
                "authors": [{
                    "id": null,
                    "firstName": "Дмитрий",
                    "lastName": "Рейн",
                    "middleName": "Константинович",
                    "address": "Address",
                    "snils": "88012234682",
                    "mobilePhone": "+79776020338",
                    "email": "room083@gmail.com",
                    "employment": "UNEMPLOYED",
                    "fioDative": "Рейну Дмитрию Константиновичу",
                    "fioGenitive": "Рейна Дмитрия Константиновича",
                    "fioNominative": "Дмитрий Константинович Рейн"
                }],
                "file": [{
                    "id": null,
                    "storageFileId": "1b7588a7-ae19-4142-97a3-cbe0e085e173",
                    "name": "some_name",
                    "extension": "txt",
                    "size": 12334,
                    "pageCount": 12,
                    "uploadDate": "2023-04-25T11:26:04.3694004+03:00",
                    "archivedDate": null,
                    "creator": {
                        "id": null,
                        "firstName": "Дмитрий",
                        "lastName": "Рейн",
                        "middleName": "Константинович",
                        "address": "Address",
                        "photoUrl": null,
                        "fioDative": "Рейну Дмитрию Константиновичу",
                        "fioNominative": "Дмитрий Константинович Рейн",
                        "fioGenitive": "Рейна Дмитрия Константиновича",
                        "externalId": "1ef2eeb4-c861-4b48-b682-e9eb08c0f163",
                        "phone": "+79776020338",
                        "workPhone": "+79786039037",
                        "workEmail": "room083@gmail.com",
                        "birthDate": "2023-04-25T11:26:04.3683958+03:00",
                        "username": "reyn_d",
                        "creationDate": null,
                        "archivedDate": null,
                        "notification": null
                    }
                }],
                "question": [],
                "appealStatus": null,
                "sendingMethod": "BY_EMAIL",
                "answeringMethod": "BY_EMAIL"
            }""";
    public static final String APPEAL_WITH_INVALID_FIELDS = "invalid";

    private TestJsonStrings() {
    }
}
