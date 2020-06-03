package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.example.sbertest.db.response.GetUserTestDoneInProcent;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcentCompletedTestsApiResponse extends BaseResponse{
    List<GetUserTestDoneInProcent> result;
}
