package eu.ha3.matmos.engine0.core.implem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import eu.ha3.matmos.engine0.conv.MAtmosConvLogger;
import eu.ha3.matmos.engine0.core.implem.abstractions.ProviderCollection;
import eu.ha3.matmos.engine0.core.interfaces.Data;
import eu.ha3.matmos.engine0.core.interfaces.Dependable;
import eu.ha3.matmos.engine0.core.interfaces.Evaluated;
import eu.ha3.matmos.engine0.core.interfaces.EventInterface;
import eu.ha3.matmos.engine0.core.interfaces.Named;
import eu.ha3.matmos.engine0.core.interfaces.Provider;
import eu.ha3.matmos.engine0.core.interfaces.ReferenceTime;
import eu.ha3.matmos.engine0.core.interfaces.SheetCommander;
import eu.ha3.matmos.engine0.core.interfaces.SheetIndex;
import eu.ha3.matmos.engine0.core.interfaces.Simulated;
import eu.ha3.matmos.engine0.core.interfaces.SoundRelay;

/* x-placeholder */

/**
 * Stores a Knowledge.
 */
public class Knowledge implements Evaluated, Simulated
{
	private Data data;
	
	// 
	
	//private final Map<String, Dynamic> dynamicMapped = new TreeMap<String, Dynamic>();
	private final Map<String, Condition> conditionMapped = new TreeMap<String, Condition>();
	private final Map<String, Junction> junctionMapped = new TreeMap<String, Junction>();
	private final Map<String, Machine> machineMapped = new TreeMap<String, Machine>();
	private final Map<String, Event> eventMapped = new TreeMap<String, Event>();
	
	private final SheetCommander sheetCommander = new SheetCommander() {
		@Override
		public int version(SheetIndex sheetIndex)
		{
			return Knowledge.this.data.getSheet(sheetIndex.getSheet()).version(sheetIndex.getIndex());
		}
		
		@Override
		public boolean listHas(String constantX, String value)
		{
			// XXX 2014-01-16
			return false;
		}
		
		@Override
		public Object get(SheetIndex sheetIndex)
		{
			return Knowledge.this.data.getSheet(sheetIndex.getSheet()).get(sheetIndex.getIndex());
		}
		
		@Override
		public boolean exists(SheetIndex sheetIndex)
		{
			return Knowledge.this.data.getSheet(sheetIndex.getSheet()).exists(sheetIndex.getIndex());
		}
	};
	//private final Provider<Dynamic> dynamicProvider = new MappedProvider<Dynamic>(this.dynamicMapped);
	private final Provider<Condition> conditionProvider = new MappedProvider<Condition>(this.conditionMapped);
	private final Provider<Junction> junctionProvider = new MappedProvider<Junction>(this.junctionMapped);
	private final Provider<Machine> machineProvider = new MappedProvider<Machine>(this.machineMapped);
	private final Provider<Event> eventProvider = new MappedProvider<Event>(this.eventMapped);
	
	private final ProviderCollection providerCollection;
	
	//
	
	private final SoundRelay relay;
	private final ReferenceTime time;
	
	public Knowledge(SoundRelay relay, ReferenceTime time)
	{
		this.relay = relay;
		this.time = time;
		
		this.providerCollection =
			new Providers(
				this.time, this.relay, this.sheetCommander, this.conditionProvider, this.junctionProvider,
				this.machineProvider, this.eventProvider);
		
		this.data = new SelfGeneratingData(GenericSheet.class);
	}
	
	public ProviderCollection obtainProviders()
	{
		return this.providerCollection;
	}
	
	public SheetCommander obtainSheetCommander()
	{
		return this.sheetCommander;
	}
	
	public void addKnowledge(List<Named> namedThings)
	{
		for (Named n : namedThings)
		{
			if (n instanceof Condition)
			{
				this.conditionMapped.put(n.getName(), (Condition) n);
			}
			else if (n instanceof Junction)
			{
				this.junctionMapped.put(n.getName(), (Junction) n);
			}
			else if (n instanceof Machine)
			{
				this.machineMapped.put(n.getName(), (Machine) n);
			}
			else if (n instanceof Event)
			{
				this.eventMapped.put(n.getName(), (Event) n);
			}
		}
	}
	
	public void compile()
	{
		purge(this.machineMapped, this.junctionMapped, "junctions");
		purge(this.junctionMapped, this.conditionMapped, "conditions");
	}
	
	/**
	 * This method must return an object that can be modified afterwards by
	 * something else.
	 * 
	 * @return
	 */
	public Set<String> calculateRequiredModules()
	{
		Set<String> requiredModules = new TreeSet<String>();
		for (Condition c : this.conditionMapped.values())
		{
			requiredModules.addAll(c.getDependencies());
		}
		
		return requiredModules;
	}
	
	private void purge(
		Map<String, ? extends Dependable> superior, Map<String, ? extends Dependable> inferior, String inferiorName)
	{
		Set<String> requirements = new TreeSet<String>();
		Set<String> unused = new TreeSet<String>();
		Set<String> missing = new TreeSet<String>();
		
		for (Dependable m : superior.values())
		{
			requirements.addAll(m.getDependencies());
		}
		
		unused.addAll(inferior.keySet());
		unused.removeAll(requirements);
		
		missing.addAll(requirements);
		missing.removeAll(inferior.keySet());
		
		if (missing.size() > 0)
		{
			MAtmosConvLogger.warning("Missing " + inferiorName + ": " + Arrays.toString(missing.toArray()));
		}
		
		if (unused.size() > 0)
		{
			MAtmosConvLogger.warning("Unused " + inferiorName + ": " + Arrays.toString(unused.toArray()));
			for (String junk : unused)
			{
				inferior.remove(junk);
			}
		}
	}
	
	public void cacheSounds()
	{
		for (EventInterface event : this.eventMapped.values())
		{
			event.cacheSounds();
		}
	}
	
	@Override
	public void simulate()
	{
		this.relay.routine();
		for (Machine m : this.machineMapped.values())
		{
			m.simulate();
		}
	}
	
	@Override
	public void evaluate()
	{
		for (Evaluated o : this.conditionMapped.values())
		{
			o.evaluate();
		}
		for (Evaluated o : this.junctionMapped.values())
		{
			o.evaluate();
		}
		for (Evaluated o : this.machineMapped.values())
		{
			o.evaluate();
		}
	}
}
