package org.neptrueworks.xenohermes.domain.common.command

import org.neptrueworks.xenohermes.domain.common.aggregation.Aggregatable
import org.neptrueworks.xenohermes.domain.common.models.DomainEntity

/**
 * A command is a significant action, invoked by a user or a domain service, 
 * that might change the state of the domain, affecting the domain logic.
 * 
 *
 * **Pattern**
 * 
 * - As a domain model, representing an operation with domain semantics.
 * - As a parameter encapsulation, naturally reducing the complexity of parameter passing.
 * - As a design pattern, decoupling the invoker and the receiver.
 * - As a CQRS command, indicating an operation with side effects.
 *
 * 
 * **Implementation**
 * 
 * - Any state changes to the aggregate, can **ONLY be achieved** by command,
 * no matter what user or domain service.
 * 
 * - Commands should be **immutable**, indicating the action is an **unchangeable** operation as invoked.
 * `data class` and `val` property are recommended practice for domain commands.
 * 
 * - Commands should be encapsulated close to invocation as much as possible,
 * decapsulated close to the state changes as much as possible, protecting the **completeness** of the action.
 * 
 * It is preferrable to mark a method in aggregate root as `internal`, which only accepts a command as a parameter.
 * Invoker could only access the aggregate root through command handler.
 * 
 * - To make a complete action, different commands might be invoked. 
 * In this case, it is recommended to implement these commands as a `FacadeCommand`,
 * while these commands are defined as interfaces.
 * 
 * @see CommandHandler
 * @see Aggregatable
 */
public interface DomainCommand : DomainEntity