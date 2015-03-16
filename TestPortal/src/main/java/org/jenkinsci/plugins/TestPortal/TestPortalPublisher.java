package org.jenkinsci.plugins.TestPortal;

import java.io.IOException;

import org.apache.tools.ant.types.FileSet;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.TaskListener;
import hudson.model.Run;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import hudson.util.FormValidation;
import jenkins.tasks.SimpleBuildStep;

public class TestPortalPublisher extends Recorder implements SimpleBuildStep {
	
	 /**
     * {@link FileSet} "includes" string, like "foo/bar/*.xml"
     */
    private final String testResults;

	@DataBoundConstructor
	public TestPortalPublisher(String portalTestResults) {
		// TODO Auto-generated constructor stub
		this.testResults = portalTestResults;
		System.out.println("Constructor called " + portalTestResults);
	}	
	
	@Override
	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}

	@Override
	public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher,
			TaskListener listener) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Test Portal Publish invoked ");
		System.out.println("Test results:  " + this.testResults);
		final String testResults = run.getEnvironment(listener).expand(this.testResults);
		System.out.println("Test results from env:  " + testResults);
		
	}
	
	public String getTestPortalResults() {
		return testResults;
	}
	
	@Extension
    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {
		public String getDisplayName() {
			return "TestPortal Publisher";
		}
		

		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return true;
		}
		
		
       
    }

}
