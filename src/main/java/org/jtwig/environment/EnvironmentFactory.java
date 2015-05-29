package org.jtwig.environment;

import org.jtwig.functions.resolver.FunctionResolverFactory;
import org.jtwig.i18n.MessageResolverFactory;
import org.jtwig.model.expression.lists.EnumerationListStrategyFactory;
import org.jtwig.parser.JtwigParserFactory;
import org.jtwig.property.PropertyResolverFactory;
import org.jtwig.resource.resolver.ResourceResolverFactory;

public class EnvironmentFactory {
    private final JtwigParserFactory jtwigParserFactory;
    private final ResourceResolverFactory resourceResolverFactory;
    private final FunctionResolverFactory functionResolverFactory;
    private final PropertyResolverFactory propertyResolverFactory;
    private final EnumerationListStrategyFactory enumerationListStrategyFactory;

    public EnvironmentFactory () {
        this(new JtwigParserFactory(), new ResourceResolverFactory(), new FunctionResolverFactory(), new PropertyResolverFactory(), new EnumerationListStrategyFactory(), new MessageResolverFactory());
    }

    public EnvironmentFactory(JtwigParserFactory jtwigParserFactory, ResourceResolverFactory resourceResolverFactory, FunctionResolverFactory functionResolverFactory, PropertyResolverFactory propertyResolverFactory, EnumerationListStrategyFactory enumerationListStrategyFactory, MessageResolverFactory messageResolverFactory) {
        this.jtwigParserFactory = jtwigParserFactory;
        this.resourceResolverFactory = resourceResolverFactory;
        this.functionResolverFactory = functionResolverFactory;
        this.propertyResolverFactory = propertyResolverFactory;
        this.enumerationListStrategyFactory = enumerationListStrategyFactory;
    }

    public Environment create(EnvironmentConfiguration environmentConfiguration) {
        return new Environment(
                jtwigParserFactory.create(environmentConfiguration.getJtwigParserConfiguration()),
                environmentConfiguration.getParameters(),
                resourceResolverFactory.create(environmentConfiguration.getResourceResolverConfiguration()),
                functionResolverFactory.create(environmentConfiguration.getFunctionResolverConfiguration()),
                propertyResolverFactory.create(environmentConfiguration.getPropertyResolverConfiguration()),
                environmentConfiguration.getRenderConfiguration(),
                environmentConfiguration.getValueConfiguration(),
                enumerationListStrategyFactory.create(environmentConfiguration.getEnumerationListConfiguration())
        );
    }
}
