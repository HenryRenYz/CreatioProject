package com.henryrenyz.creatiolib.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;

@Name("Name of Scoreboard Objective")
@Description({"Returns the name of objective"})
@Since("0.1.00")
public class Expr_scoreboardObjectiveName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(Expr_scoreboardObjectiveName.class, String.class, ExpressionType.COMBINED, "[the] name of scoreboard %objective%");
    }

    private Expression<Objective> obj;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        obj = (Expression<Objective>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "[the] name of scoreboard %objective%";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        try {
            return new String[] {obj.getSingle(event).getName()};
        } catch (NullPointerException e) {
            return null;
        }
    }
}
