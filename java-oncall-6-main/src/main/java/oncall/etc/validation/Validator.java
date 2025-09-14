package oncall.etc.validation;

public interface Validator<T> {
    void validate(T t);
}