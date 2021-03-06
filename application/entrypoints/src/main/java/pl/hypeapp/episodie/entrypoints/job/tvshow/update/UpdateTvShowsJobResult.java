package pl.hypeapp.episodie.entrypoints.job.tvshow.update;

import pl.hypeapp.episodie.core.usecase.job.RecordJobResultUseCase;

import java.util.Date;

public class UpdateTvShowsJobResult {

    private final Date date = new Date();

    private final RecordJobResultUseCase recordJobResultUseCase;

    public UpdateTvShowsJobResult(RecordJobResultUseCase recordJobResultUseCase) {
        this.recordJobResultUseCase = recordJobResultUseCase;
    }

    public void recordJobSuccessfulResult(String jobName) {
        recordJobResultUseCase.recordSuccessfulJob(jobName, date);
    }

    public void recordJobUnsuccessfulResult(String jobName, String message) {
        recordJobResultUseCase.recordUnsuccessfulJob(jobName, message, date);
    }

}
