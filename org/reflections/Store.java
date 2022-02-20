//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.reflections;

import com.google.common.base.*;
import java.util.concurrent.*;
import java.util.*;
import com.google.common.collect.*;

public class Store
{
    private transient boolean concurrent;
    private final Map<String, Multimap<String, String>> storeMap;
    
    protected Store() {
        this.storeMap = new HashMap<String, Multimap<String, String>>();
        this.concurrent = false;
    }
    
    public Store(final Configuration configuration) {
        this.storeMap = new HashMap<String, Multimap<String, String>>();
        this.concurrent = (configuration.getExecutorService() != null);
    }
    
    public Set<String> keySet() {
        return this.storeMap.keySet();
    }
    
    public Multimap<String, String> getOrCreate(final String index) {
        Multimap<String, String> mmap = this.storeMap.get(index);
        if (mmap == null) {
            final SetMultimap<String, String> multimap = (SetMultimap<String, String>)Multimaps.newSetMultimap((Map)new HashMap(), (Supplier)new Supplier<Set<String>>() {
                public Set<String> get() {
                    return (Set<String>)Sets.newSetFromMap((Map)new ConcurrentHashMap());
                }
            });
            mmap = (Multimap<String, String>)(this.concurrent ? Multimaps.synchronizedSetMultimap((SetMultimap)multimap) : multimap);
            this.storeMap.put(index, mmap);
        }
        return mmap;
    }
    
    public Multimap<String, String> get(final String index) {
        final Multimap<String, String> mmap = this.storeMap.get(index);
        if (mmap == null) {
            throw new ReflectionsException("Scanner " + index + " was not configured");
        }
        return mmap;
    }
    
    public Iterable<String> get(final String index, final String... keys) {
        return this.get(index, Arrays.asList(keys));
    }
    
    public Iterable<String> get(final String index, final Iterable<String> keys) {
        final Multimap<String, String> mmap = this.get(index);
        final IterableChain<String> result = new IterableChain<String>();
        for (final String key : keys) {
            ((IterableChain<Object>)result).addAll(mmap.get((Object)key));
        }
        return result;
    }
    
    private Iterable<String> getAllIncluding(final String index, final Iterable<String> keys, final IterableChain<String> result) {
        ((IterableChain<Object>)result).addAll(keys);
        for (final String key : keys) {
            final Iterable<String> values = this.get(index, key);
            if (values.iterator().hasNext()) {
                this.getAllIncluding(index, values, result);
            }
        }
        return result;
    }
    
    public Iterable<String> getAll(final String index, final String key) {
        return this.getAllIncluding(index, this.get(index, key), new IterableChain<String>());
    }
    
    public Iterable<String> getAll(final String index, final Iterable<String> keys) {
        return this.getAllIncluding(index, this.get(index, keys), new IterableChain<String>());
    }
    
    private static class IterableChain<T> implements Iterable<T>
    {
        private final List<Iterable<T>> chain;
        
        private IterableChain() {
            this.chain = (List<Iterable<T>>)Lists.newArrayList();
        }
        
        private void addAll(final Iterable<T> iterable) {
            this.chain.add(iterable);
        }
        
        @Override
        public Iterator<T> iterator() {
            return Iterables.concat((Iterable)this.chain).iterator();
        }
    }
}
