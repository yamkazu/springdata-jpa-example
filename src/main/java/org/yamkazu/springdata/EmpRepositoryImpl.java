package org.yamkazu.springdata;


public class EmpRepositoryImpl implements EmpRepositoryCustom {

    @Override
    public String echo(String message) {
        return message;
    }

}
