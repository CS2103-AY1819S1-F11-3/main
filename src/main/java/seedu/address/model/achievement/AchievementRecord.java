package seedu.address.model.achievement;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents a record of the user's achievements while using the task manager.
 * Achievements include the experience points(xp) earned by completing the task and the level a user has reached.
 * Guarantees: details are present and not null.
 */
public class AchievementRecord {

    private Xp xp;
    private Level level;
    private int numTaskCompleted;

    private Calendar nextDayBreakPoint;
    private int xpValueByDay;
    private int numTaskCompletedByDay;

    private Calendar nextWeekBreakPoint;
    private int xpValueByWeek;
    private int numTaskCompletedByWeek;

    /**
     * Constructs a {@code AchievementRecord}.
     * Xp value is initialized to 0 and Level initialized to LEVEL_1.
     */
    public AchievementRecord() {
        setUpAchievementRecord();
        setUpAchievementByDay();
        setUpAchievementByWeek();
    }

    /**
     * Constructs a {@code AchievementRecord}.
     * Both fields must be present.
     */
    public AchievementRecord(Xp xp, Level level, int numTaskCompleted, Calendar nextDayBreakPoint,
                             int numTaskCompletedByDay, int xpValueByDay, Calendar nextWeekBreakPoint,
                             int numTaskCompletedByWeek, int xpValueByWeek) {
        requireAllNonNull(xp, level, numTaskCompleted, nextDayBreakPoint, numTaskCompletedByDay, xpValueByDay,
                nextWeekBreakPoint, numTaskCompletedByWeek, xpValueByWeek);
        this.xp = xp;
        this.level = level;
        this.numTaskCompleted = numTaskCompleted;
        
        this.nextDayBreakPoint = nextDayBreakPoint;
        this.numTaskCompletedByDay = numTaskCompletedByDay;
        this.xpValueByDay = xpValueByDay;
        
        this.nextWeekBreakPoint = nextWeekBreakPoint;
        this.numTaskCompletedByWeek = numTaskCompletedByWeek;
        this.xpValueByWeek = xpValueByWeek;
    }

    public Level getLevel() {
        return level;
    }

//    public void setLevel(Level level) {
//        this.level = level;
//    }

    public int getXpValue() {
        return xp.getXp();
    }

//    public Xp getXp() {
//        return xp;
//    }
//
//    public void setXp(Xp xp) {
//        this.xp = xp;
//    }
//
//    public int getNumTaskCompleted() {
//        return numTaskCompleted;
//    }
//
//    public void setNumTaskCompleted(int numTaskCompleted) {
//        this.numTaskCompleted = numTaskCompleted;
//    }
//
//    public Calendar getNextDayBreakPoint() {
//        return nextDayBreakPoint;
//    }
//
//    public void setNextDayBreakPoint(Calendar nextDayBreakPoint) {
//        this.nextDayBreakPoint = nextDayBreakPoint;
//    }
//
//    public int getXpValueByDay() {
//        return xpValueByDay;
//    }
//
//    public void setXpValueByDay(int xpValueByDay) {
//        this.xpValueByDay = xpValueByDay;
//    }
//
//    public int getNumTaskCompletedByDay() {
//        return numTaskCompletedByDay;
//    }
//
//    public void setNumTaskCompletedByDay(int numTaskCompletedByDay) {
//        this.numTaskCompletedByDay = numTaskCompletedByDay;
//    }
//
//    public Calendar getNextWeekBreakPoint() {
//        return nextWeekBreakPoint;
//    }
//
//    public void setNextWeekBreakPoint(Calendar nextWeekBreakPoint) {
//        this.nextWeekBreakPoint = nextWeekBreakPoint;
//    }
//
//    public int getXpValueByWeek() {
//        return xpValueByWeek;
//    }
//
//    public void setXpValueByWeek(int xpValueByWeek) {
//        this.xpValueByWeek = xpValueByWeek;
//    }
//
//    public int getNumTaskCompletedByWeek() {
//        return numTaskCompletedByWeek;
//    }
//
//    public void setNumTaskCompletedByWeek(int numTaskCompletedByWeek) {
//        this.numTaskCompletedByWeek = numTaskCompletedByWeek;
//    }

    private void setUpAchievementRecord() {
        this.xp = new Xp();
        this.level = Level.LEVEL_1;
        this.numTaskCompleted = 0;
    }
    
    private void setUpAchievementByDay() {
        assert nextDayBreakPoint == null;
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DAY_OF_MONTH, 1);
        nextDayBreakPoint = date;
        numTaskCompletedByDay = 0;
        xpValueByDay = 0;
    }

    private void setUpAchievementByWeek() {
        assert nextWeekBreakPoint == null;
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DAY_OF_MONTH, 7);
        nextWeekBreakPoint = date;
        numTaskCompletedByWeek = 0;
        xpValueByWeek = 0;
    }
    /**
     * Resets the existing data of this {@code AchievementRecord} with {@code newData}.
     */
    public void resetData(AchievementRecord newData) {
        requireNonNull(newData);

//        setXp(newData.getXp());
//        setLevel(newData.getLevel());
        xp = newData.xp;
        level = newData.level;
        numTaskCompleted = newData.numTaskCompleted;
        nextDayBreakPoint = newData.nextDayBreakPoint;
        numTaskCompletedByDay = newData.numTaskCompletedByDay;
        xpValueByDay = newData.xpValueByDay;
        nextWeekBreakPoint = newData.nextWeekBreakPoint;
        numTaskCompletedByWeek = newData.numTaskCompletedByWeek;
        xpValueByWeek = newData.xpValueByWeek;
    }

    /**
     * Updates the Xp field of this {@code AchievementRecord} with new xp value.
     * Triggers the update of Level with xp.
     */
    public void updateXp(Integer xp) {
        requireNonNull(xp);

        Integer newXpValue = this.getXpValue() + xp;
        this.xp = new Xp(newXpValue);
        updateLevelWithXp(newXpValue);
    }

    /**
     * Updates the Level field of this {@code AchievementRecord} with new xp value.
     */
    public void updateLevelWithXp(Integer xp) {
        Level newLevel = getMatchingLevel(xp);
        if (!this.level.equals(newLevel)) {
            level = newLevel;
        }
    }

    /**
     * Returns the corresponding {@code Level} of the current Xp value.
     * Maximum level is level 5.
     */
    public Level getMatchingLevel(Integer xp) {
        if (xp < Level.LEVEL_1.getMaxXp()) {
            return Level.LEVEL_1;
        } else if (xp < Level.LEVEL_2.getMaxXp()) {
            return Level.LEVEL_2;
        } else if (xp < Level.LEVEL_3.getMaxXp()) {
            return Level.LEVEL_3;
        } else if (xp < Level.LEVEL_4.getMaxXp()) {
            return Level.LEVEL_4;
        } else {
            return Level.LEVEL_5;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AchievementRecord // instanceof handles nulls
                && xp.equals(((AchievementRecord) other).xp))
                && level.equals(((AchievementRecord) other).level)
                && numTaskCompleted == ((AchievementRecord) other).numTaskCompleted
                && nextDayBreakPoint.equals(((AchievementRecord) other).nextDayBreakPoint)
                && numTaskCompletedByDay == ((AchievementRecord) other).numTaskCompletedByDay
                && xpValueByDay == ((AchievementRecord) other).xpValueByDay
                && nextWeekBreakPoint.equals(((AchievementRecord) other).nextWeekBreakPoint)
                && numTaskCompletedByWeek == ((AchievementRecord) other).numTaskCompletedByWeek
                && xpValueByWeek == ((AchievementRecord) other).xpValueByWeek;
    }
}
