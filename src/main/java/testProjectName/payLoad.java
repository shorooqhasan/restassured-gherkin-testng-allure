package testProjectName;

public class payLoad {




    public static String createValidPlaceViaPOST(String latitude,String longitude){
        String createPlace = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": "+latitude+",\n" +
                "    \"lng\":"+longitude+" },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Gaurd Of Honour!\",\n" +
                "  \"phone_number\": \"(02) 9374 4000\",\n" +
                "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                "  \"types\": [\"restaurant\"],\n" +
                "  \"website\": \"http://www.google.com.au/\",\n" +
                "  \"language\": \"en-AU\"\n" +
                "}";
        return createPlace;
    }

    public static String deletePlace(String placeId){
        String deletePlace = "{\n" +
                "  \"place_id\": \""+placeId+"\"\n" +
                "}";
        return deletePlace;
    }

    public static String createClaim(int allowedAsset){
        String createClaim = "{\n" +
                "\t\"marketId\": \"DE\",\n" +
                "    \"assetCount\": \""+allowedAsset+"\",\n" +
                "    \n" +
                "    \"advisor\": {\n" +
                "    \t\"firstname\": \"Nikolai\",\n" +
                "    \t\"lastname\": \"Ansó García\",\n" +
                "    \t\"phone\": \"+491122334455\"\n" +
                "\t},\n" +
                "    \"callbackContact\" : {\n" +
                "        \"firstname\": \"Smolartz Gosia\",\n" +
                "        \"lastname\": \"Müller\",\n" +
                "        \"phone\": \"+49123456678\",\n" +
                "        \"preferredTime\": \"[\\\"2018-02-07T11:00:00+02:00/2017-09-07T13:00:00+02:00\\\",\\\"2017-09-07T17:00:00+02:00/2017-09-07T18:00:00+02:00\\\"]\"\n" +
                "    },\n" +
                "    \"customer\" : {\n" +
                "\t\t\"address\": {\n" +
                "\t      \"addressline1\": \"C. de Yecla, 22, 5ºB\",\n" +
                "\t      \"addressline2\": \"1º Dcha\",\n" +
                "\t      \"addressline3\": \"Spain\",\n" +
                "\t      \"city\": \"Valencia\",\n" +
                "\t      \"countryCode\": \"ES\",\n" +
                "\t      \"houseNo\": \"43\",\n" +
                "\t      \"street\": \"Carrer de Yecla\",\n" +
                "\t      \"zipcode\": \"46021\"\n" +
                "\t    },\n" +
                "\t\t\"currency\": \"EUR\",\n" +
                "\t\t\"salutation\": \"MRS\",\n" +
                "\t    \"firstname\": \"Margarita Arleta\",\n" +
                "\t    \"lastname1\": \"López\",\n" +
                "\t    \"lastname2\": \"Gonzaléz\",\n" +
                "\t    \"landlinePhone\": \"+4930123456789\",\n" +
                "\t    \"mobilePhone\": \"+49157123456789\",\n" +
                "\t    \"email\": \"v.lopez.gonzalez@mailtest.diconium.com\",\n" +
                "\t    \"preferredLanguage\": \"es\",\n" +
                "\t    \"ucid\": \"UCID, not transferred\"\n" +
                "    },\n" +
                "    \"vehicle\" : {\n" +
                "        \"FIN\": \"WDD12345678912345\",    \t\n" +
                "        \"mileage\": \"20000\",\n" +
                "        \"mileageUnit\":\"KM\",\n" +
                "        \"licensePlate\": \"B-DC-1234\", \n" +
                "\t    \"breakdownCover\": \"Mobilo\",\n" +
                "\t    \"breakdownCoverDetail\": \"Mobilo mobility warranty expires on 31-DEC-2017.\",\n" +
                "\t    \"operable\": \"Vehicle is operable, but restricted to 20 km/h.\"\n" +
                "    },\n" +
                "    \"incident\" : {\n" +
                "    \t\"referenceId\": \"\",   \t    \n" +
                "        \"dateTime\": \"2017-11-24T18:04:29.65Z\",\n" +
                "\t\t\"description\": \"Flat tire, front, right hand side. No replacement wheel. Rims okay, Yokohama 225/45 R 17 91W.\",   \n" +
                "\t\t\"courseOfEvents\": \"Tried to avoid a bicycle going the wrong direction, steered the car through a pothole.\",\n" +
                "   \t    \"overflowCaseNumber\": \"357951\",\n" +
                "        \"address\": {\n" +
                "            \"countryCode\": \"DE\",\n" +
                "            \"city\": \"Berlin\",\n" +
                "            \"zipcode\": \"12345\",\n" +
                "            \"street\": \"Kohlfurter Straße\",\n" +
                "            \"houseNo\": \"41/43\",\n" +
                "            \"addressline1\":\"Further address details.\", \n" +
                "            \"addressline2\": \"More details.\",\n" +
                "\t        \"addressline3\": \"Even more details.\"\n" +
                "       },\n" +
                "\t    \"geoLocation\": {\n" +
                "\t      \"latitude\": \"52.496587\",\n" +
                "\t      \"longitude\": \"13.418673\", \n" +
                "\t      \"description\": \"Right after Kottbusser Brücke, direction of Kottbusser Tor.\"\n" +
                "\t    }\n" +
                "    }, \n" +
                "    \"providers\": [\n" +
                "\t    {\n" +
                "\t      \"id\": \"100\",\n" +
                "\t      \"gssnOutletId\": \"GS0000580\",\n" +
                "\t      \"name\": \"Gran Vía Marqués del Turia 52, 46005 Valencia, Tel: +34 96 1224607\",\n" +
                "\t      \"type\": \"PreferredWorkshop\"\n" +
                "\t    }, \n" +
                "\t\t{\n" +
                "\t      \"id\": \"200\",\n" +
                "\t      \"gssnOutletId\": \"n/a\",\n" +
                "\t      \"name\": \"ADAC Berlin\",\n" +
                "\t      \"type\": \"RoadsideAssistance\"\n" +
                "\t    }, \n" +
                "\t    {\n" +
                "\t      \"id\": \"300\",\n" +
                "\t      \"gssnOutletId\": \"GS0000581\",\n" +
                "\t      \"name\": \"Taller Camiones, Avenida Comarques Pais Valencia 121, 46930 Quart de Poblet\",\n" +
                "\t      \"type\": \"Workshop\"\n" +
                "\t    },\n" +
                "\t    {\n" +
                "\t      \"id\": \"400\",\n" +
                "\t      \"gssnOutletId\": \"n/a\",\n" +
                "\t      \"name\": \"RESSA Valencia\",\n" +
                "\t      \"type\": \"TowingCompany\"\n" +
                "\t    }\t    \n" +
                "\t]\n" +
                "}";
        return createClaim;
    }

}
