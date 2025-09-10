package oncall.Utils.validation;

public interface Validator<T> {
    void validate(T t);
}