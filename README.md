# DeviceHive Java Plugin Template

This is a simple template that is meant to provide a sample structure for any DeviceHive plugins written in Java.

## Configuration
These configuration options are required for the plugin to properly start and connect to the DeviceHive Server
* server.address - Network address to which the plugin should bind to.
* server.port - Plugin HTTP port.
* dh.user.login - Login for DeviceHive user.
* dh.user.password - Password for DeviceHive user.
* dh.endpoint.auth - Endpoint where the DeviceHive Authentication server can be reached.
* dh.endpoint.plugin - Endpoint where the DeviceHive Plugin Management server can be reached.
* plugin.name - Name of the plugin. Must be unique.
* plugin.description - Short description of the plugin.
* plugin.health-check-endpoint - name of the healthcheck endpoint that the plugin should expose (default is `health`)

The following configuration options allow us to configure which messages from the server should the plugin listen to:
* plugin.device-id - Device GUID.
* plugin.network-ids - Comma-separated list of network ids.
* plugin.device-type-ids - Comma-separated list of device type ids.
* plugin.names - Comma-separated list of action names.
* plugin.return-commands - Whether the plugin should listen to commands (true or false).
* plugin.return-updated-commands - Whether the plugin should listen to command updates (true or false).
* plugin.return-notifications - Whether the plugin should listen to notifications (true or false).