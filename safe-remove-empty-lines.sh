#!/bin/bash

echo "Safe empty line removal with Git verification..."
echo "==============================================="

# 1. Make sure we're in a clean state
if ! git diff --quiet; then
    echo "⚠ Working directory is not clean. Please commit or stash changes first."
    exit 1
fi

# 2. Create a branch for this operation
branch_name="cleanup/remove-empty-lines-$(date +%Y%m%d-%H%M%S)"
git checkout -b "$branch_name"

echo "Created branch: $branch_name"

# 3. Run the cleanup
echo "Running empty line cleanup..."
find . -name "*.java" -type f | while read -r java_file; do
    awk '
    /^[[:space:]]*$/ {
        if (!empty_line_printed) {
            print
            empty_line_printed = 1
        }
        next
    }
    {
        empty_line_printed = 0
        print
    }
    ' "$java_file" > "$java_file.tmp" && mv "$java_file.tmp" "$java_file"
done

# 4. Verify changes
echo ""
echo "Verifying changes..."

if git diff --ignore-blank-lines --quiet; then
    echo "✓ Verification passed: Only blank lines were modified"
    
    # Show summary
    echo ""
    echo "Summary of changes:"
    git diff --stat
    
    # Commit the changes
    git add .
    git commit -m "Remove consecutive empty lines from Java files

Only whitespace changes - removed multiple consecutive empty lines,
keeping single empty lines for readability."
    
    echo ""
    echo "Changes committed to branch: $branch_name"
    echo "To merge back to main:"
    echo "  git checkout main"
    echo "  git merge $branch_name"
    echo "  git branch -d $branch_name"
    
else
    echo "⚠ WARNING: Non-blank line changes detected!"
    echo "Rolling back changes..."
    git checkout .
    git checkout main
    git branch -D "$branch_name"
    echo "Changes rolled back. Please investigate the script."
fi