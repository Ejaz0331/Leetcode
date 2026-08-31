class Solution {
public:
    int minSplitMerge(vector<int>& nums1, vector<int>& nums2) {
        if(nums1 == nums2) return 0;
        int n = nums1.size();
        set<vector<int>> vis;
        vis.insert(nums1);
        queue<pair<vector<int>, int>> q;
        q.push({nums1, 0});
        int ans = 0;

        while(!q.empty()) {
            vector<int> node = q.front().first;
            int d = q.front().second;
            q.pop();

            for(int i = 0;i < n;i++) {
                for(int j = i;j < n;j++) {
                    vector<int> sub(node.begin() + i, node.begin()+j+1);
                    vector<int> bachahua;
                    for(int k = 0;k < i;k++) {
                        bachahua.push_back(node[k]);
                    }
                    for(int k = j+1;k< n;k++) {
                        bachahua.push_back(node[k]);
                    }

                    for(int a = 0; a <= bachahua.size(); a++) {
                        if(a == i) continue; // same position
                        vector<int> newlyformed;
                        for(int k = 0;k < a;k++) {
                            newlyformed.push_back(bachahua[k]);
                        }
                        for(int k = 0;k < sub.size();k++) {
                            newlyformed.push_back(sub[k]);
                        }
                        for(int k = a;k < bachahua.size();k++) {
                            newlyformed.push_back(bachahua[k]);
                        }
                        if(vis.count(newlyformed)) continue;
                        if(newlyformed == nums2) {
                            ans = d+1;
                            return ans;
                        }
                        vis.insert(newlyformed);
                        q.push({newlyformed, d+1});
                    }
                }
            }
        }
        return ans;
    }
};