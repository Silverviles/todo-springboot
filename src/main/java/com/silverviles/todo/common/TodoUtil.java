package com.silverviles.todo.common;

import com.silverviles.todo.common.constants.SortType;
import com.silverviles.todo.masterService.dao.Todo;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class TodoUtil {
    public Page<Todo> sortTodos(Page<Todo> todos, Integer sort) {
        Stream<Todo> todoStream = todos.stream();

        SortType sortType = SortType.values()[sort];
        todoStream = switch (sortType) {
            case TITLE_ASC -> todoStream.sorted(Comparator.comparing(Todo::getTitle));
            case TITLE_DESC -> todoStream.sorted(Comparator.comparing(Todo::getTitle).reversed());
            case DATE_ASC -> todoStream.sorted(Comparator.comparing(Todo::getCreatedDate));
            case DATE_DESC -> todoStream.sorted(Comparator.comparing(Todo::getCreatedDate).reversed());
            case STATUS_TRUE -> todoStream.sorted(Comparator.comparing(Todo::isDone));
            case STATUS_FALSE -> todoStream.sorted(Comparator.comparing(Todo::isDone).reversed());
        };

        List<Todo> sortedTodos = todoStream.collect(Collectors.toList());
        return new PageImpl<>(sortedTodos, todos.getPageable(), todos.getTotalElements());
    }
}
