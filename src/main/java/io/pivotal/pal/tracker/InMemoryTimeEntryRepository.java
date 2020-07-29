package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.copyOf;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long,TimeEntry> entries = new HashMap<>();
    private long nextId = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = nextId++;
        TimeEntry timeEntry1 = new TimeEntry(id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),timeEntry.getHours());
        entries.put(id,timeEntry1);
        return timeEntry1;
    }

    @Override
    public TimeEntry find(long id) {
        return entries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return copyOf(entries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (entries.containsKey(id)) {
            TimeEntry timeEntry1 = new TimeEntry(id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(), timeEntry.getHours());
            entries.put(id, timeEntry1);
            return timeEntry1;
        }
        else{
            return null;
        }
    }

    @Override
    public void delete(long id) {
        entries.remove(id);
   }
}
