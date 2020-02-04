package ml.classifiers.utils;


public interface IVoter<ItemType, CriterionType, ResultType> {

    /**
     * Add an item to be used in the voting process
     */
    void addItem(ItemType item, CriterionType criterionType);

    /**
     * Return the result of the vote where max is the
     * maximum best items to take into account
     */
    ResultType getResult(int max);

}
