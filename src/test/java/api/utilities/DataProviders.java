package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {

        String path = System.getProperty("user.dir")+"//Petstore_testdata.xlsx";
        ExcelUtil excel = new ExcelUtil(path, "Sheet1");
        Object[][] data = excel.getTestData();
        excel.closeWorkbook();

        return data;
    }

    @DataProvider(name = "excelData_usernames")
    public Object[] getExcelData_usernames() {

        String path = System.getProperty("user.dir")+"//Petstore_testdata.xlsx";
        ExcelUtil excel = new ExcelUtil(path, "Sheet1");
        Object[]data = excel.getTestData_usernames();
        excel.closeWorkbook();

        return data;
    }
}
