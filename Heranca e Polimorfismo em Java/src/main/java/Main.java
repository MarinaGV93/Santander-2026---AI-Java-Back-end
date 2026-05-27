import domain.Employee;
import domain.Manager;
import domain.Salesman;

public class Main {
    public static void main(String[] args) {
        //Pegar domain.Employee
        //domain.Employee employee = new domain.Employee();

        //Uma variável do tipo da classe abstrata
        // domain.Employee manager = new domain.Manager();

        //Pegar domain.Manager
        //domain.Manager manager = new domain.Manager();

        //O 'manager' é um 'employee', só que está como setado que ele é um 'manager'. Permitido pq o 'manager' é um 'employee'
        //domain.Employee manager = new domain.Manager();

        printEmployee(new Manager());
        printEmployee(new Salesman());
    }

    public static void printEmployee(Employee employee) {
        //Só pode quando o 'employee' estiver no mesmo package
        // employee.code = "";

        //Separar as classes com o nome da classe
        System.out.printf("=======%s=======\n", employee.getClass().getCanonicalName());

        //Se employee é uma instancia de domain.Manager
        // if (employee instanceof domain.Manager manager){
        //
        // }

        switch (employee){
            case Manager manager -> {
                manager.setCode("123");

                //Em 'manager', tera acesso tanto nas propriedades do 'employee', quanto do 'manager'
                manager.setName("Marina");

                manager.setSalary(5000);

                //Converter 'employee' para 'manager'
                // ((domain.Manager)employee)
                manager.setLogin("marina");
                // ((domain.Manager)employee)
                manager.setPassword("123456");
                manager.setCommission(1200);


                //Imprimir o nome
                System.out.println("Código: " + manager.getCode());
                System.out.println("Salário: " + manager.getSalary());
                System.out.println("Nome: " + manager.getName());
                System.out.println("Login: " + manager.getLogin());
                System.out.println("Senha: " + manager.getPassword());
                System.out.println("Comissão: " + manager.getCommission());
                // System.out.println("Salário com extra: " + manager.getFullSalary(500));
            }
            case Salesman salesman -> {
                salesman.setCode("456");
                salesman.setName("Larissa");
                salesman.setSalary(2800);
                salesman.setPercentPerSold(10);
                salesman.setSoldAmount(1000);


                System.out.println("Código: " + salesman.getCode());
                System.out.println("Salário: " + salesman.getSalary());
                System.out.println("Nome: " + salesman.getName());
                System.out.println("Percentual de venda: " + salesman.getPercentPerSold());
                System.out.println("Valor vendido: " + salesman.getSoldAmount());
            }

            //Esta permitindo nao usar 'default' pq o 'employee' esta como 'sealed'
        }
        System.out.println("Salário com extra: " + employee.getFullSalary(500));
        System.out.println("Salário completo: " + employee.getFullSalary());
        System.out.println("========================\n");
    }
}
