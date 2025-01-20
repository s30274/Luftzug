package pl.edu.pjatk.luftzug.service.mappers;

public interface IMap<TValue, TResult> {

    TResult map(TValue item);
}
