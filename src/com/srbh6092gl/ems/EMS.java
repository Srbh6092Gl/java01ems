package com.srbh6092gl.ems;

import java.util.Scanner;

public class EMS {
    static Employee[] employees = new Employee[10];
    static Scanner S = new Scanner(System.in);
    static int last = -1;
    public static void main(String[] args) throws InvalidEmployeeIdException {
        int id=0;
        System.out.println("Admin mode");
        System.out.println("1: add");
        System.out.println("2: remove");
        System.out.println("3: display");
        System.out.println("4: update");
        System.out.println("5: exit");
        boolean notExit=true;
        while(notExit){
            System.out.println("\n****************");
            System.out.println("Enter 1, 2, 3, 4 or 5");
            short choice = S.nextShort();
            switch (choice){
                case 1:
                    if(last==employees.length-1)
                        System.out.println("Overflow");
                    else
                        postEmployee(++id);
                    break;
                case 2:
                    if(last==-1)
                        System.out.println("underflow");
                    else {
                        System.out.println("Enter id: id");
                        int empId = S.nextInt();
                        try {
                            remove(empId);
                        } catch (InvalidEmployeeIdException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    //Displaying null values too just to visualize the placement of data
                    for (Employee employee: employees)
                        System.out.println(employee);
                    break;
                case 4:
                    System.out.println("Enter employee id");
                    int empId = S.nextInt();
                    try{
                        int index = hasEmp(empId);
                        putEmployee(index);
                    } catch (InvalidEmployeeIdException e) {
                        System.out.println(e.getMessage());;
                    }
                    break;
                case 5:
                    notExit=false;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void putEmployee(int index) {
        System.out.println("Enter name, designation, salary");
        S=new Scanner(System.in);
        String name = S.nextLine();
        String designation = S.nextLine();
        double salary = S.nextDouble();
        employees[index].setName(name);
        employees[index].setDesignation(designation);
        employees[index].setSalary(salary);
        System.out.println("Updated: "+employees[index]);
    }

    private static void remove(int empId) throws InvalidEmployeeIdException {
        int i;
        for(i=0; i<= last;i++)
            if(empId==employees[i].getId()){
                System.out.println(employees[i]+" is deleted from "+i);
                break;
            }
        if (empId==employees[i].getId()&&i<=last){
            while(i<=last) {
                employees[i] = employees[i + 1];
                i++;
            }
            employees[last]=null;
            last--;
        }
        else throw new InvalidEmployeeIdException("No employee with id: "+empId);
    }

    private static void postEmployee(int id) {
        System.out.println("Enter name, designation, salary");
        S=new Scanner(System.in);
        String name = S.nextLine();
        String designation = S.nextLine();
        double salary = S.nextDouble();
        employees[++last]=new Employee(id,name,designation,salary);
    }
    private static int hasEmp(int id) throws InvalidEmployeeIdException {
        for(int i=0;i<=last; i++)
            if(id==employees[i].getId())
                return i;
        throw new InvalidEmployeeIdException("No employee with id: "+id);
    }
}
