package service;

public abstract class BaseService {
    protected String[] args;
    public abstract void handleRequest();

    public BaseService(String[] args) {
        this.args = args;
    }
}
