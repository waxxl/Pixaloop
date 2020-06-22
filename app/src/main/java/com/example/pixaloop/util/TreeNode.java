package com.example.pixaloop.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TreeNode<T> {
    @NonNull
    public final T a;
    public ImmutableList<TreeNode<T>> b;

    public TreeNode(@NonNull T t, @Nullable List<TreeNode<T>> list) {
        Preconditions.checkNotNull(t);
        this.a = t;
        if(list == null) {
            //this.b = new ArrayList<TreeNode<T>>()
        } else {
            this.b = (ImmutableList<TreeNode<T>>) list;
        }
        this.b = ImmutableList.copyOf(list);
    }

    @NonNull
    public ImmutableList<TreeNode<T>> a() {
        return this.b;
    }

    @NonNull
    public T b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TreeNode.class != obj.getClass()) {
            return false;
        }
        TreeNode treeNode = (TreeNode) obj;
        if (!Objects.equals(this.a, treeNode.a) || !Objects.equals(this.b, treeNode.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.a, this.b});
    }

    @NonNull
    public ImmutableList<TreeNode<T>> a(TreeNode<T> treeNode) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, treeNode);
        return ImmutableList.copyOf(arrayList);
    }

    public final boolean a(@NonNull List<TreeNode<T>> list, TreeNode<T> treeNode) {
        if (equals(treeNode)) {
            list.add(this);
            return true;
        }
        UnmodifiableIterator<TreeNode<T>> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().a(list, treeNode)) {
                list.add(0, this);
                return true;
            }
        }
        return false;
    }
}
