package org.kie.kogito.jitexecutor.tester.models;

public class Applicant {
    Integer salary;

    public Applicant(Integer salary){
        this.salary = salary;
    }
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}
