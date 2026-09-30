package br.com.sma_bad_smells.sma.domain.models;

import java.util.List;

public class NormalizedBatch {
    private long id;
    private final List<NormalizedLogs> logs;
    private boolean stored   = false; // já foi ao PersistenceAgent?
    private boolean analyzed = false; // já foi ao PatternAgent?

    public NormalizedBatch(long l, List<NormalizedLogs> logs) {
        this.logs = logs;
        this.id = l;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<NormalizedLogs> getLogs() { return logs; }

    public boolean isStored()   { return stored; }
    public boolean isAnalyzed() { return analyzed; }

    public void markStored()   { this.stored = true; }
    public void markAnalyzed() { this.analyzed = true; }

    public boolean isFullyConsumed() { return stored && analyzed; }
}
