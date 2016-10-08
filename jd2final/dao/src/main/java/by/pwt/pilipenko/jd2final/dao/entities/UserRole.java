package by.pwt.pilipenko.jd2final.dao.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apilipenka on 8/5/2016.
 */

@Entity
@Table(name = "user_roles")
public class UserRole extends Type {

    private static final long serialVersionUID = -2060426102854785438L;


    Set<Command> commands;

    public UserRole() {
        super();
        commands = new HashSet<Command>();
    }

    public UserRole(int id, String name, String dsecription) {
        super(id, name, dsecription);
        commands = new HashSet<Command>();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        return result;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_commands", catalog = "payments", joinColumns = {
            @JoinColumn(name = "user_role_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "command_id",
                    nullable = false, updatable = false)}
    )
    public Set<Command> getCommands() {
        return commands;
    }


    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }

    public void addCommands(Set<Command> commands) {
        this.commands.addAll(commands);
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public List<Command> findAccounts(Command command) {
        List<Command> finded = new ArrayList<Command>();
        for (Command icommand : commands) {
            if ((command.getCommand() != null && command.getCommand().equals(icommand.getCommand())))
                finded.add(icommand);
        }
        return finded;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return getClass() == obj.getClass();
    }

    @Override
    public String toString() {
        return "UserRole [id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", commands=" + commands + "]";

    }

}
