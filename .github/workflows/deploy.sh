#!/bin/bash

# Variables
APP_NAME="demo-employee-portal"
JAR_FILE="demo-employee-portal.jar"
DEPLOY_DIR="/path/to/deploy"
BACKUP_DIR="/path/to/backup"
SERVICE_NAME="demo-employee-portal"

# Create backup of the current version
echo "Creating backup of current application..."
if [ -f "$DEPLOY_DIR/$JAR_FILE" ]; then
  mv "$DEPLOY_DIR/$JAR_FILE" "$BACKUP_DIR/$APP_NAME-$(date +%F-%T).jar"
  echo "Backup created at $BACKUP_DIR"
else
  echo "No existing application found, skipping backup."
fi

# Move new JAR to deployment directory
echo "Deploying new version of the application..."
mv "/tmp/$JAR_FILE" "$DEPLOY_DIR/$JAR_FILE"

# Restart the application service
echo "Restarting application service..."
systemctl restart "$SERVICE_NAME"

# Check service status
echo "Checking service status..."
systemctl status "$SERVICE_NAME" --no-pager

echo "Deployment completed successfully."
