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

        NodeClassificationTaskFactory classificationTaskFactory = new NodeClassificationTaskFactory(applicationManager, networkViewManager, visualMappingManager, discreteMappingFactory);
        Properties classificationProps = new Properties();
        classificationProps.put("preferredMenu", "Apps.MyApp");
        classificationProps.put("title", "Node Classification");
        registerService(context, classificationTaskFactory, org.cytoscape.work.TaskFactory.class, classificationProps);

        GroupNodesTaskFactory groupTaskFactory = new GroupNodesTaskFactory(applicationManager, networkViewManager);
        Properties groupProps = new Properties();
        groupProps.put("preferredMenu", "Apps.MyApp");
        groupProps.put("title", "Group Nodes by Label");
        registerService(context, groupTaskFactory, org.cytoscape.work.TaskFactory.class, groupProps);
    }
}