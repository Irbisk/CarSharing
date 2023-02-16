package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CompanyImplementation implements CompanyActions{

    static List<Company> companyList = new ArrayList<>();

    public CompanyImplementation() {
    }

    @Override
    public void createCompany(Company company) {
        String sql = "INSERT INTO COMPANY (NAME) " +
                "VALUES ('" + company.getName() + "')";
        System.out.println(sql);
        DBHandler.insert(sql);

    }

    @Override
    public void getCompaniesList() {
        DBHandler.updateCompanies();
    }
}
