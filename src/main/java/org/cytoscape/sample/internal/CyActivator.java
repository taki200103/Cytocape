package org.cytoscape.sample.internal;

import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.osgi.framework.BundleContext;
import java.util.Properties;

public class CyActivator extends AbstractCyActivator {
    @Override
    public void start(BundleContext context) {
        CyApplicationManager applicationManager = getService(context, CyApplicationManager.class);
        CyNetworkViewManager networkViewManager = getService(context, CyNetworkViewManager.class);
        VisualMappingManager visualMappingManager = getService(context, VisualMappingManager.class);
        VisualMappingFunctionFactory discreteMappingFactory = getService(context, VisualMappingFunctionFactory.class);

        NodeClassificationTaskFactory taskFactory = new NodeClassificationTaskFactory(applicationManager, networkViewManager, visualMappingManager, discreteMappingFactory);
        Properties properties = new Properties();
        properties.put("preferredMenu", "Apps.MyApp");
        properties.put("title", "Node Classification");
        registerService(context, taskFactory, org.cytoscape.work.TaskFactory.class, properties);
    }
}
