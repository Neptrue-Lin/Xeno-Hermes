package org.neptrueworks.xenohermes.domain.common.aggregation

/**
 * An aggregate is a cluster of associated objects treated as a **functional operation unit** 
 * in the purpose of achieving specific **invariant business rules**.
 * 
 * An aggregate MUST get accessed through aggregate root preventing inconsistent state changes, 
 * indicating aggregate root is the **ONLY entry point** to aggregate.
 * 
 * - In consideration of non-invasive design, limited by Jimmer, prior aggregatable interface with complete `val` properties 
 * should be defined to be inherited by data aggregatable interface.
 *
 * - Outside the domain, the aggregator implementation of data aggregatable interface keeps track of Draft mutation.
 * 
 * - The aggregate root implementation of domain aggregatable interface should be abstract, 
 * and only variant properties should be defined as `var` with `protected set`.
 * 
 * - For the functions achieving invariant business rules, they should be defined as `internal`,
 * to ensure the aggregate root is the **ONLY entry point** to aggregate.
 */
public interface Aggregatable 
